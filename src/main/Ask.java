package main;

import objets.*;


public class Ask {
	

	public static Tag getTag(String tagInitial) {
		String tag = tagInitial.replace(" ", "%20").toLowerCase();
		Tag t = RequeteBDD.getTag(tag);
		if (t == null) t = RequeteAPI.getTag(tag);
		return t;



	}

    public static Album getAlbum(String albumInitial,String artisteInitial){


        String album = albumInitial.replace(" ", "%20");
        String artiste = artisteInitial.replace(" ", "%20");
        album=album.toLowerCase();
        artiste=artiste.toLowerCase();
        Album a = RequeteBDD.getAlbum(album,artiste);
        if (a == null) a = RequeteAPI.getAlbum(album,artiste);
        return a;
    }
	
	public static Artist getArtist(String artistInitial) {
		String artist = artistInitial.replace(" ", "%20").toLowerCase();
		Artist a = RequeteBDD.getArtist(artist);
		if (a == null) a = RequeteAPI.getArtist(artist);
		return a;
	}
	
}
