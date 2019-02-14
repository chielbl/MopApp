package be.ehb.demo4.recycleUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.demo4.R;
import be.ehb.demo4.model.Mop;

public class MopAdapter extends RecyclerView.Adapter<MopAdapter.MopViewHolder> {

    //Viewholder pattern
    //Klasse die verwijzing bijhoud naar elementen in layout
    //Klasse is enkel hier nodig -> inner class
    class MopViewHolder extends RecyclerView.ViewHolder{

        //Verwijzing naar elementen in layout
        final TextView tvMop;
        final TextView tvClou;


        public MopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMop = itemView.findViewById(R.id.tv_mop);
            tvClou = itemView.findViewById(R.id.tv_clou);
        }
    }

    private ArrayList<Mop> items;

    public MopAdapter(ArrayList<Mop> items) {
        this.items = items;
    }


    //Hoe ziet de rij er uit
    @NonNull
    @Override
    public MopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Viewgroup, waarbinnen wordt alles getekend
        Context context = viewGroup.getContext();

        //Binnen die context effectief layout.xml omzetten naar iets in het scherm
        View cardRijView = LayoutInflater.from(context).inflate(R.layout.mop_card, viewGroup, false);

        //Nieuwe viewholder op basis van de getekende layout
        return new MopViewHolder(cardRijView);
    }

    //Rijen opvullen
    @Override
    public void onBindViewHolder(@NonNull MopViewHolder mopViewHolder, int position) { // i -> position

        Mop mopVoorDeRij = items.get(position);

        mopViewHolder.tvMop.setText(mopVoorDeRij.getMop());
        mopViewHolder.tvClou.setText(mopVoorDeRij.getClou());
    }

    //Hoeveel rijen wordt er getekend
    @Override
    public int getItemCount() {
        return items.size();
    }
}
