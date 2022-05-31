package tests;

import org.bson.Document;

import main.HTTPTools;

public class HTMLTest {
	
	public static Document getTrackInfo(String artist) {
		Document res = null;
		try {
			
			String url = "https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=7f3c4b6c3b001b8a97a4976cc72b5a3b&artist=Cher&album=Believe&format=json";
			HTTPTools httpTools = new HTTPTools();
			String jsonResponse = httpTools.sendGet(url);
			Document docLastFm = Document.parse(jsonResponse);
			
			res = docLastFm;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
}
