package main;

import objets.Tag;

public class Ask {
	
	public static Tag getTag(String tagInitial) {
		String tag = tagInitial.replace(" ", "%20");
		Tag t = RequeteBDD.getTag(tag);
		if (t == null) t = RequeteAPI.getTag(tag);
		return t;
	}
	
}
