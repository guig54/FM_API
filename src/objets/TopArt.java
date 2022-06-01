package objets;


import java.util.ArrayList;

public class TopArt{

    private  String date;
    private  ArrayList<String> lartiste;

    public TopArt(String date, ArrayList<String> lartiste) {
        this.date = date;
        this.lartiste = lartiste;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getLartiste() {
        return lartiste;
    }

    public void setLartiste(ArrayList<String> lartiste) {
        this.lartiste = lartiste;
    }


    @Override
    public String toString() {
        return "TopArt{" +
                "date='" + date + '\'' +
                ", lartiste=" + lartiste +
                '}';
    }
}
