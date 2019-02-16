package be.ehb.demo4.model;

import java.util.ArrayList;

public class MopDAO {
    private static final MopDAO ourInstance = new MopDAO();
    private ArrayList<Mop> mopLijst = new ArrayList<>();

    public static MopDAO getInstance() {
        return ourInstance;
    }

    private MopDAO() {
        mopLijst.add(new Mop("Het is oud en het vliegt", "Een bejaardelaar"));
        mopLijst.add(new Mop("Het is rood en vliegt in de klas", "Een ongestelde vraag"));
        mopLijst.add(new Mop("Het is geel en het weegt niet veel", "Lichtgeel"));
        mopLijst.add(new Mop("Het is orangje en als het regent is het weg", "De mannen van de gemeente"));
    }

    public ArrayList<Mop> getMopLijst() {
        return mopLijst;
    }

    public void addMop(Mop nieuwMop){
        mopLijst.add(nieuwMop);
    }
}
