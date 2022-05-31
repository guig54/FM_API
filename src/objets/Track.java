package objets;

public class Track {

    private String name;
    private String nameArtiste;

    private String mbid;
    private int duration,rank;


    public Track(String name, String nameArtiste, int duration, int rank) {
        this.name = name;
        this.nameArtiste = nameArtiste;
        this.duration = duration;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getNameArtiste() {
        return nameArtiste;
    }

    public String getMbid() {
        return mbid;
    }

    public int getDuration() {
        return duration;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameArtiste(String nameArtiste) {
        this.nameArtiste = nameArtiste;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", nameArtiste='" + nameArtiste + '\'' +
                ", mbid='" + mbid + '\'' +
                ", duration=" + duration +
                ", rank=" + rank +
                '}';
    }
}
