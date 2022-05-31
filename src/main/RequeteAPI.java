package main;

import java.util.ArrayList;

import org.bson.Document;

import fonctions.fonctions;
import objets.*;

public class RequeteAPI {
	
	public final static String API_KEY = "7f3c4b6c3b001b8a97a4976cc72b5a3b";
	
	public static Tag getTag(String tag) {
		System.out.println("API "+tag);
		Tag res = null;
		//String tag = fonctions.replaceSpace(tagInitial);
		try {
			String url = "http://ws.audioscrobbler.com/2.0/?method=tag.getinfo&tag="+tag+"&api_key="+API_KEY+"&format=json";
			HTTPTools httpTools = new HTTPTools();
			String jsonResponse = httpTools.sendGet(url);
			Document enveloppe = Document.parse(jsonResponse);
			Document doc = (Document)enveloppe.get("tag");
			Document wiki = (Document)doc.get("wiki");
			res = new Tag(doc.getString("name"),doc.getInteger("total"),doc.getInteger("reach"),wiki.getString("summary"),doc.getString("content"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		RequeteBDD.addTag(res);
		
		return res;
		
	}
	
	public static Artist getArtist(String artist) {
		System.out.println("API "+artist);
		Artist res = null;
		//String artist = fonctions.replaceSpace(artistInitial);
		try {
			String url = "http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist="+artist+"&api_key="+API_KEY+"&format=json";
			HTTPTools httpTools = new HTTPTools();
			String jsonResponse = httpTools.sendGet(url);
			Document enveloppe = Document.parse(jsonResponse);
			Document doc = (Document)enveloppe.get("artist");
			Document stats = (Document)doc.get("stats");
			Document similar = (Document)doc.get("similar");
			ArrayList<Document> simiList = (ArrayList<Document>) similar.get("artist");
			ArrayList<String> simiString = new ArrayList<String>();
			for(Document o : simiList) {
				simiString.add(o.getString("name"));
			}
			Document tags = (Document)doc.get("tags");
			ArrayList<Document> tagsList = (ArrayList<Document>) tags.get("tag");
			ArrayList<String> tagsString = new ArrayList<String>();
			for(Document o : tagsList) {
				tagsString.add(o.getString("name"));
			}
			Document bio = (Document)doc.get("bio");
			System.out.println(simiString);
			System.out.println(tagsString);
			System.out.println(stats.getString("playcount"));
			res = new Artist(doc.getString("name").toLowerCase(),simiString,stats.getString("playcount"),stats.getString("listeners"),tagsString,bio.getString("published"),bio.getString("summary"),bio.getString("content"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		RequeteBDD.addArtist(res);
		
		return res;
		
	}
}
