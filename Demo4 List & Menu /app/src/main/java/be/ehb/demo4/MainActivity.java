package be.ehb.demo4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import be.ehb.demo4.model.MopDAO;
import be.ehb.demo4.recycleUtilities.MopAdapter;
import be.ehb.demo4.recycleUtilities.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMopjes;
    private MopAdapter adapter;


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
}
