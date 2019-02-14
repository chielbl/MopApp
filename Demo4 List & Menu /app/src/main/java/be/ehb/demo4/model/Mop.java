package be.ehb.demo4.model;

import android.support.v7.widget.CardView;
import android.widget.TextView;

public class Mop {

    private String mop;
    private String clou;


    public Mop() {
    }

    public Mop(String mop, String clou) {
        this.mop = mop;
        this.clou = clou;
    }

    public String getMop() {
        return mop;
    }

    public void setMop(String mop) {
        this.mop = mop;
    }

    public String getClou() {
        return clou;
    }

    public void setClou(String clou) {
        this.clou = clou;
    }
}
