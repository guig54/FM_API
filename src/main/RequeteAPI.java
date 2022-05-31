package main;

import org.bson.Document;

public class RequeteAPI {
	
	public final static String API_KEY = "7f3c4b6c3b001b8a97a4976cc72b5a3b";
	
	public static void getTag(String tag) {
		Document res = null;
		try {

			String url = "http://ws.audioscrobbler.com/2.0/?method=tag.getinfo&tag="+tag+"&api_key="+API_KEY+"&format=json";
			HTTPTools httpTools = new HTTPTools();
			String jsonResponse = httpTools.sendGet(url);
			Document docLastFm = Document.parse(jsonResponse);
			//System.out.println(docLastFm.get("tag"));
			Document sousDoc = (Document)docLastFm.get("tag");
			System.out.println(sousDoc.get("name",String.class));
			System.out.println(sousDoc.get("total",Integer.class));
			Document wiki = (Document)sousDoc.get("wiki");
			System.out.println(wiki.get("summary"));
			//res = docLastFm;

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
