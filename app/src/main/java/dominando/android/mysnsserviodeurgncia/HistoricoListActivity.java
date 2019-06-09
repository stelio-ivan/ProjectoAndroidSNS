package dominando.android.mysnsserviodeurgncia;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import dominando.android.mysnsserviodeurgncia.model.Historico;
import dominando.android.mysnsserviodeurgncia.model.HistoricoProvider;


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_view_historicos, menu);

        //getting the search view from the menu
        MenuItem searchViewItem = menu.findItem(R.id.menuSearch_historicos);

        //getting search manager from systemservice
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //getting the search view
        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        //you can put a hint for the search input field
        searchView.setQueryHint("Procurar historico...");
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
    public void onItemClick(int position) {
        Log.d("tag", "OnClick(): Clicked");
        Intent intent =  new Intent(this, Historico_detalhe_activity.class);
        startActivity(intent);
    }

}
