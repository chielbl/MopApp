package be.ehb.demo4.recycleUtilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    //Nodig om in de activity te laten invullen
    public interface ClickListener{
        void onClick(View view, int position);
    }

    //Listener is een verwijzing naar de listener un de activity
    private ClickListener clickListener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        //Wat staat er op coordinaat x & y in de recycler
        //Coordinaat van waar een vinger het scherm raakt
        View rij = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        if (rij != null && motionEvent.getAction() == MotionEvent.ACTION_UP){
            //Methode uitvoeren, dit stuurt dan door naar activity
            clickListener.onClick(rij, recyclerView.getChildAdapterPosition(rij));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
