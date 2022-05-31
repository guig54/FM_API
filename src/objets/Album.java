package objets;

import java.util.ArrayList;

public class Album {
    private String artist, mbid,name;
    private ArrayList<String> tname;
    private String listeners,playcount;
    private ArrayList<Track> tracks;

    public Album(String artist, String mbid, ArrayList<String> tname,String name, String listeners, String playcount, ArrayList<Track> tracks) {
        this.artist = artist;
        this.mbid = mbid;
        this.name = name;
        this.tname = tname;
        this.listeners = listeners;
        this.playcount = playcount;
        this.tracks = tracks;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTname() {
        return tname;
    }

    public void setTname(ArrayList<String> tname) {
        this.tname = tname;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Album{" +
                "artist='" + artist + '\'' +
                ", mbid='" + mbid + '\'' +
                ", name='" + name + '\'' +
                ", tname=" + tname +
                ", listeners=" + listeners +
                ", playcount=" + playcount +
                ", tracks=" + tracks +
                '}';
    }
}
