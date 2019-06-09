package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

import dominando.android.mysnsserviodeurgncia.model.CheckIn;
import dominando.android.mysnsserviodeurgncia.model.CheckInProviderRealm;
import dominando.android.mysnsserviodeurgncia.model.CheckOut;
import dominando.android.mysnsserviodeurgncia.model.CheckOutProviderRealm;
import dominando.android.mysnsserviodeurgncia.model.Hospital;

public class hospital_detalhe_activity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private CheckInProviderRealm mCheckInProviderRealm;
    private CheckOutProviderRealm mCheckOutProviderRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detalhe);

        mCheckInProviderRealm=CheckInProviderRealm.getInstance(this);
        mCheckOutProviderRealm=CheckOutProviderRealm.getInstance(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_hospital_detalhe);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        Hospital hospitalModel = null;
        if(getIntent().hasExtra("hospital_selecionado")){
            hospitalModel= getIntent().getParcelableExtra("hospital_selecionado");
            setData(hospitalModel);
        }
    }

    private void setData(Hospital hospital) {
        TextView id;
        TextView name =  findViewById(R.id.nome_hospital);
            name.setText(hospital.getName());
        TextView description;
        TextView longitude;
        TextView latitude;
        TextView address =findViewById(R.id.value_morada);
            address.setText(hospital.getAddress());
        TextView phone = findViewById(R.id.value_contato);
            phone.setText(hospital.getPhone());
        TextView email = findViewById(R.id.value_email);
            email.setText(hospital.getEmail());
        TextView district;
        TextView standbyTimesUrl;
        TextView shareStandbyTimes;
        TextView institutionURL = findViewById(R.id.value_url);
            institutionURL.setText(hospital.getInstitutionURL());
        TextView distanciaKm;
    }

    public void executarAcao(View view) {
        Uri uri = null;
        Intent intent = null;
        String nomeHospital= String.valueOf(R.id.nome_hospital);
        String uuid= UUID.randomUUID().toString();
        switch (view.getId()) {
            case R.id.l_l_endereco:
                intent = new Intent(getApplicationContext(), Mapa.class);
                startActivity(intent);
                break;
            case R.id.l_l_phone:
                TextView tv = (TextView) findViewById(R.id.value_contato);
                uri = Uri.parse("tel:" + tv.getText().toString());
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.l_l_url:
                tv = (TextView) findViewById(R.id.value_url);
                uri = Uri.parse("http://" + tv.getText().toString());
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btn_checkIn:
                CheckIn checkIn= new CheckIn(nomeHospital, uuid);

                mCheckInProviderRealm.guardarCheckin(checkIn);
                break;

            case R.id.btn_checkOut:
                CheckOut checkOut= new CheckOut(nomeHospital, uuid);
                mCheckOutProviderRealm.guardarCheckout(checkOut);
                break;
        }
    }
}
