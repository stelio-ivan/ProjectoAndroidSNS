package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;



public class sugestao_form extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestao_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_sugestao);
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

        final RadioButton geral= (RadioButton) findViewById(R.id.radioButtonGeral);
    }


    public void displaySugestao(View view) {
        switch (view.getId()) {
            case R.id.btn_sugestao:
                Intent intent = new Intent(getApplicationContext(), HospitalListActivity.class);
                intent.putExtra("opcao", "sugestao");
                startActivity(intent);
        }
    }
}
