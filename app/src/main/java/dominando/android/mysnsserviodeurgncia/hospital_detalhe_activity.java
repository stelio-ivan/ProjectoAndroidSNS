package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class hospital_detalhe_activity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detalhe);

        //obter paramentros
        //getInComingIntent();

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
    }

    public void getInComingIntent() {
        if(getIntent().hasExtra("nome_hospital")){
            Log.d("TAG", "getInComingIntent: found intent extras");

            String nome_hospital = getIntent().getStringExtra("nome_hospital");

            setDataToActivity(nome_hospital);
        }
    }

    public void setDataToActivity(String nome_hospital){
        System.out.println("Nome: " + nome_hospital);
        TextView tv_nome = (TextView) findViewById(R.id.hospital_list_item_name);
        System.out.println("Nome view: " + tv_nome.getText());
        tv_nome.setText(nome_hospital);
    }
}
