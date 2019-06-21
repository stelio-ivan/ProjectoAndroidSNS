package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Historico;
import dominando.android.mysnsserviodeurgncia.model.HistoricoProvider;
import dominando.android.mysnsserviodeurgncia.model.Hospital;


public class hospital_detalhe_activity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private HistoricoProvider mHistoricoProvider;
    Historico h1= new Historico();
    Button buttonCheckIn;
    Button buttonCheckOut;
    Button buttonDeslocar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detalhe);



        mHistoricoProvider=HistoricoProvider.getInstance(this);

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

        buttonDeslocar= (Button)findViewById(R.id.btn_deslocal);


        buttonCheckIn=(Button)findViewById(R.id.btn_checkIn);
        buttonCheckIn.setEnabled(false);

        buttonCheckOut=(Button)findViewById(R.id.btn_checkOut);
        buttonCheckOut.setEnabled(false);

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

            case  R.id.btn_deslocal:
                Log.d("Deslocar", "fui clicado");
                buttonDeslocar.setBackgroundColor(getResources().getColor(R.color.secondaryColor));
                buttonCheckOut.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonDeslocar.setEnabled(false);
                buttonCheckIn.setEnabled(true);
                break;



            case R.id.btn_checkIn:
               buttonCheckIn.setBackgroundColor(getResources().getColor(R.color.secondaryColor));
               buttonDeslocar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               buttonCheckIn.setEnabled(false);
               buttonCheckOut.setEnabled(true);

               h1=new Historico();
               TextView nHospital=(TextView) findViewById(R.id.nome_hospital);
               String nomeHospitalString=nHospital.getText().toString();
               h1.setNomeHospital(nomeHospitalString);
               h1.setCheckInDate();

                break;
            case  R.id.btn_checkOut:
                buttonDeslocar.setEnabled(true);
                buttonCheckOut.setEnabled(false);
                buttonCheckIn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                buttonCheckOut.setBackgroundColor(getResources().getColor(R.color.secondaryColor));

                h1.setCheckOutDate();
                mHistoricoProvider.guardarHistorico(h1);

                break;
        }
    }

    public void trocaCores(){
        if(buttonCheckIn.isEnabled()){
            buttonCheckIn.setBackgroundResource(R.color.colorPrimary);
        }
    }
}
