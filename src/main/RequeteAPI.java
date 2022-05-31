package main;

import org.bson.Document;

public class RequeteAPI {
	public static final String API_KEY = "7f3c4b6c3b001b8a97a4976cc72b5a3b";

    public static Document getTag(String tag){
        String url = "http://ws.audioscrobbler.com/2.0/?method=tag.getinfo&tag="+tag+"&api_key="+API_KEY+"&format=json";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document docLastFm = Document.parse(jsonResponse);
        System.out.println(docLastFm);
        return docLastFm;
    }
}
