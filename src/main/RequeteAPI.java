package main;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;

import objets.Album;
import objets.Track;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Locale;

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
			Document docLastFm = Document.parse(jsonResponse);
			//System.out.println(docLastFm.get("tag"));
			Document sousDoc = (Document)docLastFm.get("tag");

            collection.insertOne(sousDoc);

			System.out.println(sousDoc.get("name",String.class));
			System.out.println(sousDoc.get("total",Integer.class));
			Document wiki = (Document)sousDoc.get("wiki");
			System.out.println(wiki.get("summary"));
			//res = docLastFm;

		} catch (Exception e) {
			System.out.println(e);
		}

		




		RequeteBDD.addTag(res);

		return res;

	}

    public static Album getAlbum(String album, String artiste) {
        System.out.println("API "+ album);
        Album res = null;
        //String tag = fonctions.replaceSpace(tagInitial);
        try {

            String url = "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key="+API_KEY+"&artist="+artiste+"&album="+album+"&format=json";
            HTTPTools httpTools = new HTTPTools();
            String jsonResponse = httpTools.sendGet(url);
            Document enveloppe = Document.parse(jsonResponse);
            Document doc = (Document)enveloppe.get("album");
            Document listetags = (Document)doc.get("tags");
            ArrayList<Document> tags = (ArrayList<Document>) listetags.get("tag");
            ArrayList<String>  tagNames=new ArrayList<>();
            for(Document o : tags) {
                tagNames.add(o.getString("name"));
            }
            Document tracks=(Document)doc.get("tracks");
            ArrayList<Document> track = (ArrayList<Document>) tracks.get("track");
            ArrayList<Track> ltrack=new ArrayList<>();
            for (Document t : track){
                int duree=0;
                if(t.getInteger("duration")!=null)
                    duree=t.getInteger("duration");

                String tname= t.getString("name");
                Document attr=(Document)t.get("@attr");
                int rank=attr.getInteger("rank");
                Document tartiste =(Document)t.get("artist");
                String nameArtiste=tartiste.getString("name");
                String mbid = tartiste.getString("mbid");
                Track trackf=new Track(tname,nameArtiste,mbid,duree,rank);
                ltrack.add(trackf);
            }

            res = new Album(doc.getString("artist").toLowerCase(),doc.getString("mbid"),tagNames,doc.getString("name").toLowerCase(),doc.getString("listeners"),doc.getString("playcount"),ltrack);

        } catch (Exception e) {
            System.out.println(e);
        }

        RequeteBDD.addAlbum(res);
        return res;

    }

}
