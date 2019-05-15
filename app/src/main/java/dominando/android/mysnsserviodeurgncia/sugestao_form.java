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
        mDrawerLayout = (DrawerLayout) findViewById(R.id.sugestao_activity);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final RadioButton geral= (RadioButton) findViewById(R.id.radioButtonGeral);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
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
