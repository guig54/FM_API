package main;

import fonctions.interfaces;
import objets.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;


public class Ask {
	//Classe servant à discerner si on recupère une requete sur l'API ou la BDD
	//Si on fait une requete toujours sur l'API ou toujour sur la BDD on ira sur les classes respectifs

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

    public static void getTopMonde(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        TopArt ta=RequeteBDD.getTopMondeBdd(date);
        if (ta==null){
            TopArt ta_old=RequeteBDD.getAncienTopMondeBdd();
            TopArt ta_new= RequeteAPI.getTop10Monde(date);
            Collections.shuffle(ta_old.getLartiste());
            System.out.println("la nouvelle  :" +ta_new+ "\n-------\n avant :  "+ta_old);
            interfaces.classement(ta_old.getLartiste(),ta_new.getLartiste());
        }

    }
	
}
