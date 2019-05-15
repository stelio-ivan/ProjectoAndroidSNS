package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Hospital;
import dominando.android.mysnsserviodeurgncia.model.HospitalProvider;

public class HospitalListActivity extends AppCompatActivity implements HospitalListAdapter.OnItemListener  {

    List<Hospital> hospitais;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

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

        String opcao = "hospitais"; //valor default para carregar a activity_hospital_list.xml
        String gravidadeUrgencia = "Grave";
        String tipoUrgencia = "Geral";
        //get StringExtra From emissor(susgestaoForm)
        opcao = getIntent().getStringExtra("opcao");
        if(opcao != null && opcao.equals("sugestao")){
            System.out.println("A obter sugestao...");
            hospitais = HospitalProvider.getInstance().getSugestao(gravidadeUrgencia, tipoUrgencia);
            mAdapter = new HospitalListAdapter(hospitais, gravidadeUrgencia, tipoUrgencia, this);
        } else {
            System.out.println("A obter hospitais...");
            hospitais = HospitalProvider.getInstance().getHospitais();
            mAdapter = new HospitalListAdapter(hospitais, this);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Log.d("tag", "OnClick(): Clicked");

        Intent intent =  new Intent(this, hospital_detalhe_activity.class);
        //System.out.println("Posicao: " + position);
        //System.out.println("Nome: " + hospitais.get(position).getNome());
        intent.putExtra("nome_hospital", hospitais.get(position).getNome());
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
