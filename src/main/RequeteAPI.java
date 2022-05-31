package main;

import org.bson.Document;

import fonctions.fonctions;
import objets.Tag;

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
}
