package be.ehb.demo4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import be.ehb.demo4.model.MopDAO;
import be.ehb.demo4.recycleUtilities.MopAdapter;
import be.ehb.demo4.recycleUtilities.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMopjes;
    private MopAdapter adapter;


    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        //op enter gedrukt
        @Override
        public boolean onQueryTextSubmit(String query) {
            adapter.getFilter().filter(query);
            return false;
        }
        //als een karakter is getypt || verwijderd
        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    };

    //Aanmaken Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoofd_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        searchView.setOnQueryTextListener(searchListener);

        return super.onCreateOptionsMenu(menu);
    }

    //Iets selecteren van uit het menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Welke item
        if (item.getItemId() == R.id.mi_add){
            //Start navigatie
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verwijzen naar recycler
        rvMopjes = findViewById(R.id.rv_mopjes);
        //Adapter, hoe recycler opvullen
        adapter = new MopAdapter(MopDAO.getInstance().getMopLijst());
        rvMopjes.setAdapter(adapter);
        //Hoe elemneten weergeven (horizontale lijst?, verticale lijst?)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMopjes.setLayoutManager(layoutManager);

        //Listener toevoegen aan recyclerview
        rvMopjes.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("Test", MopDAO.getInstance().getMopLijst().get(position).getMop());

                MopAdapter.MopViewHolder verwijzing = (MopAdapter.MopViewHolder) view.getTag();
                verwijzing.tvClou.setVisibility(View.VISIBLE);
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Addapter update
        adapter = new MopAdapter(MopDAO.getInstance().getMopLijst());
        //Recyclerview updaten vanuit adapter
        adapter.notifyDataSetChanged();
    }
}
