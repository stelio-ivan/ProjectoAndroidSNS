package dominando.android.mysnsserviodeurgncia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Historico;
import dominando.android.mysnsserviodeurgncia.model.HistoricoProvider;
import dominando.android.mysnsserviodeurgncia.model.Hospital;
import dominando.android.mysnsserviodeurgncia.services.Result;
import dominando.android.mysnsserviodeurgncia.services.HospitalProvider;
import dominando.android.mysnsserviodeurgncia.services.HospitalService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalListActivity extends AppCompatActivity implements HospitalListAdapter.OnItemListener,
        NavigationView.OnNavigationItemSelectedListener, LocationListener {

    List<Hospital> hospitais;
    List<Hospital> hospitalModels = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    LocationManager locationManager;





    Result resultRequest = null;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        CheckPermission();
        getLocation();

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_hospitais);
        toolbar.setTitle(R.string.tile_HospitalListActivity);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.hospital_activity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);

        ///--- Implement API Call-----------------------------------------------------------------------
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HospitalService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HospitalService service = retrofit.create(HospitalService.class);

        Call<Result> call = service.getHospitais();

        //Permitir todos os acessos a internet
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Result resultRequest = call.execute().body();
            hospitalModels = resultRequest.hospitals;
        } catch (IOException e) {
            e.printStackTrace();
        }


//        for (HospitalModel hm: hospitalModels) {
//            System.out.println(hm.getAddress());
//        }

        ///----/--------------------------------------------------------

        String opcao = "hospitais"; //valor default para carregar a activity_hospital_list.xml
        String gravidadeUrgencia = "Grave";
        String tipoUrgencia = "Geral";
        //get StringExtra From emissor(susgestaoForm)
        opcao = getIntent().getStringExtra("opcao");
        if(opcao != null && opcao.equals("sugestao")){
            System.out.println("A obter sugestao...");
            hospitais = HospitalProvider.getInstance(hospitalModels).getSugestao(gravidadeUrgencia, tipoUrgencia);
            mAdapter = new HospitalListAdapter(hospitais, gravidadeUrgencia, tipoUrgencia, this);
            toolbar.setTitle(R.string.tile_SugestaoHospitais);
        } else {
            System.out.println("A obter hospitais...");
            hospitais = HospitalProvider.getInstance(hospitalModels).getHospitais();
            mAdapter = new HospitalListAdapter(hospitais, this);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        Location location = null;
        onLocationChanged(location);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLocation();
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, hospital_detalhe_activity.class);
        intent.putExtra("hospital_selecionado", hospitalModels.get(position));
        startActivity(intent);
    }


    //Função que despoleta o click no botao toggle do toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_view_hospitais, menu);

        //getting the search view from the menu
        MenuItem searchViewItem = menu.findItem(R.id.menuSearch_hospitais);

        //getting search manager from systemservice
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //getting the search view
        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        //you can put a hint for the search input field
        searchView.setQueryHint("Procurar hospital...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //by setting it true we are making it iconified
        //so the search input will show up after taping the search iconified
        //if you want to make it visible all the time make it false
        searchView.setIconifiedByDefault(true);

        //here we will get the search query
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //do the search here
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();


        switch (id) {
            case R.id.nav_conta:
                System.out.println("Clicked Conta.");
                Toast.makeText(this, "This is Conta", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_hospitais:
                Intent intent = new Intent(getApplicationContext(), HospitalListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_historico:
                intent = new Intent(getApplicationContext(), HistoricoListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_sugestao:
                intent = new Intent(getApplicationContext(), sugestao_form.class);
                startActivity(intent);
                break;
            case R.id.nav_configuracoes:
                System.out.println("Clicked Conta.");
                Toast.makeText(this, "This is Configurações", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                System.out.println("Clicked Conta.");
                Toast.makeText(this, "This is Logout", Toast.LENGTH_SHORT).show();
                break;




        }
        return false;
    }

    @Override
    public void onLocationChanged(Location location) {


        for (int position=0; position<hospitalModels.size(); position++){
            hospitalModels.get(position).setDistanciaKm(location.getLatitude(), location.getLongitude());
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(HospitalListActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Enabled new provider!" + provider,
                Toast.LENGTH_SHORT).show();
    }
}
