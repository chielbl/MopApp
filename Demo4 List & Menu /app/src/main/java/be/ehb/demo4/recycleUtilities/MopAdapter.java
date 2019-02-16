package be.ehb.demo4.recycleUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.demo4.R;
import be.ehb.demo4.model.Mop;

public class MopAdapter extends RecyclerView.Adapter<MopAdapter.MopViewHolder> implements Filterable {


    //Viewholder pattern
    //Klasse die verwijzing bijhoud naar elementen in layout
    //Klasse is enkel hier nodig -> inner class
    public class MopViewHolder extends RecyclerView.ViewHolder{

        //Verwijzing naar elementen in layout
        public final TextView tvMop;
        public final TextView tvClou;


        public MopViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            tvMop = itemView.findViewById(R.id.tv_mop);
            tvClou = itemView.findViewById(R.id.tv_clou);
        }
    }

    private ArrayList<Mop> items, filteredItems;

    public MopAdapter(ArrayList<Mop> items) {
        this.items = items;
        this.filteredItems = items;
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

        Mop mopVoorDeRij = filteredItems.get(position);

        mopViewHolder.tvMop.setText(mopVoorDeRij.getMop());
        mopViewHolder.tvClou.setText(mopVoorDeRij.getClou());
    }

    //Hoeveel rijen wordt er getekend
    @Override
    public int getItemCount() {
        return filteredItems.size();
    }


    @Override
    public Filter getFilter() {
        //anonymous class
        return new Filter() {
            //door de data gaan en enkele items uit de lijsten filteren
            //resultatenset opbouwen
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //wat is er getypt
                String zoekterm = constraint.toString();

                //is er niets getypt, toon alles
                if (zoekterm.isEmpty()){
                    filteredItems = items;
                }else{
                    //is er iets getypt, overloop alle items en kijk na of zoekterm in moppen voorkomen
                    ArrayList<Mop> tempList = new ArrayList<>();
                    for( Mop m : items ){
                        if(m.getMop().contains(zoekterm) || m.getClou().contains(zoekterm) ){
                            //geldig, toevoegen aan lijst
                            tempList.add(m);
                        }
                    }
                    filteredItems = tempList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredItems;
                return filterResults;
            }

            //resultatenset komt binnen, gebruiken om lijst te updaten met wat overblijft na het filteren
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItems = (ArrayList<Mop>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
