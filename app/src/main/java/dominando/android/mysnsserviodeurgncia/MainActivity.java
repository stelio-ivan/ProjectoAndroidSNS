package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dominando.android.mysnsserviodeurgncia.model.Historico;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Pode-se alterar o hospital_list_item por uma outra activity que pretenda testar
        Intent intent = new Intent(getApplicationContext(), HospitalListActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_mapa);
    }


}
