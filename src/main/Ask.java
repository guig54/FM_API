package main;

import objets.*;

public class Ask {
	
	public static Tag getTag(String tagInitial) {
		String tag = tagInitial.replace(" ", "%20").toLowerCase();
		Tag t = RequeteBDD.getTag(tag);
		if (t == null) t = RequeteAPI.getTag(tag);
		return t;
	}
	
	public static Artist getArtist(String artistInitial) {
		String artist = artistInitial.replace(" ", "%20").toLowerCase();
		Artist a = RequeteBDD.getArtist(artist);
		if (a == null) a = RequeteAPI.getArtist(artist);
		return a;
	}
	
}
