package dominando.android.mysnsserviodeurgncia;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Historico;
import dominando.android.mysnsserviodeurgncia.model.HistoricoProvider;
import dominando.android.mysnsserviodeurgncia.model.Hospital;
import dominando.android.mysnsserviodeurgncia.model.HospitalProvider;

public class HistoricoListActivity extends AppCompatActivity implements HistoricoListAdapter.OnItemListener {

    List<Historico> historico;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_historico);
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

        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_historicos);
        historico = HistoricoProvider.getInstance().getHistorico();

        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new HistoricoListAdapter(historico, this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Log.d("tag", "OnClick(): Clicked");
        Intent intent =  new Intent(this, Historico_detalhe_activity.class);
        startActivity(intent);
    }

}
