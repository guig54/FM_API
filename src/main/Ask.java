package main;


import org.bson.Document;


import objets.Album;
import objets.Tag;


import java.util.Locale;

public class Ask {


	public static Tag getTag(String tagInitial) {
		String tag = tagInitial.replace(" ", "%20");
		Tag t = RequeteBDD.getTag(tag);
		if (t == null) t = RequeteAPI.getTag(tag);
		return t;

	}

    public static Album getAlbum(String albumInitial,String artisteInitial){


        String album = albumInitial.replace(" ", "%20");
        String artiste = artisteInitial.replace(" ", "%20");
        album=album.toLowerCase();
        artiste=artiste.toLowerCase();
        Album a = RequeteBDD.getAlbum("Feu","Nekfeu");
        if (a == null) a = RequeteAPI.getAlbum(album,artiste);
        return a;
    }
	
}
