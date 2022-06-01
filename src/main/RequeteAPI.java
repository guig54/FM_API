package main;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;


import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Locale;

import fonctions.fonctions;
import objets.*;

import javax.print.Doc;

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
			res = new Artist(doc.getString("name").toLowerCase(),simiString,stats.getString("playcount"),stats.getString("listeners"),tagsString,bio.getString("published"),bio.getString("summary"),bio.getString("content"));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		RequeteBDD.addArtist(res);
		
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
                Track trackf=new Track(tname,nameArtiste,duree,rank);
                ltrack.add(trackf);
            }

            res = new Album(doc.getString("artist").toLowerCase(),doc.getString("mbid"),tagNames,doc.getString("name").toLowerCase(),doc.getString("listeners"),doc.getString("playcount"),ltrack);

        } catch (Exception e) {
            System.out.println(e);
        }

        RequeteBDD.addAlbum(res);
        return res;

    }

    public static void getTop10Geo(String loc){
        String url="http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country="+loc+"&api_key="+API_KEY+"&format=json&limit=10";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document res = Document.parse(jsonResponse);
        Document art=(Document) res.get("topartists");
        ArrayList<Document> lart= (ArrayList<Document>) art.get("artist");
        int i=1;
        System.out.println("-TOP ARTISTE-");
        for(Document a :lart){
            System.out.println(i+"."+a.getString("name")+" ( auditeurs :"+a.get("listeners")+" - nombre d'ecoute:"+a.get("playcount")+")");
            i++;
        }


        String url2="http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country="+loc+"&api_key="+API_KEY+"&format=json&limit=10";
        HTTPTools httpTools2 = new HTTPTools();
        String jsonResponse2 = httpTools2.sendGet(url2);
        Document res2 = Document.parse(jsonResponse2);
        Document album=(Document) res2.get("tracks");
        ArrayList<Document> lalbum= (ArrayList<Document>) album.get("track");
        int j=1;
        System.out.println("-TOP MUSIQUE-");
        for(Document a :lalbum){
            System.out.println(j+"."+a.getString("name")+" ( auditeurs :"+a.get("listeners")+" - nombre d'ecoute:"+a.get("playcount")+")");
            j++;
        }

    }

    public static TopArt getTop10Monde(String date) {
        ArrayList<String> resArt = new ArrayList<>();
        String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=" + API_KEY + "&format=json&limit=10";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document res = Document.parse(jsonResponse);
        Document artists = (Document) res.get("artists");
        ArrayList<Document> lart = (ArrayList<Document>) artists.get("artist");

        System.out.println("-TOP ARTISTES MONDE-");
        int i = 1;
        for (Document a : lart) {
            // System.out.println(i+"."+a.getString("name")+" ( auditeurs :"+a.get("listeners")+" - nombre d'ecoute:"+a.get("playcount")+")");
            i++;
            resArt.add(a.getString("name"));
        }
        TopArt ta = new TopArt(date, resArt);
        RequeteBDD.addTopArt(ta);
        System.out.println(ta);
        return ta;
    }

    public void getTopTags() {
        System.out.println(" ");
        String url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptags&api_key=" + API_KEY + "&format=json&limit=10";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document res = Document.parse(jsonResponse);
        Document tags = (Document) res.get("tags");
        ArrayList<Document> ltag = (ArrayList<Document>) tags.get("tag");
        System.out.println("-TOP TAGS MONDE-");
        int j = 1;
            for (Document a : ltag) {
                System.out.println(j+"."+a.getString("name"));
                j++;
            }
        }

    public void getTopTracks(){
        System.out.println(" ");
        String url="http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key="+API_KEY+"&format=json&limit=10";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document res = Document.parse(jsonResponse);
        Document musique=(Document) res.get("tracks");
        ArrayList<Document> lmusique= (ArrayList<Document>) musique.get("track");
        System.out.println("-TOP MUSIQUES MONDE-");
        int k=1;
        for(Document a :lmusique){
            Document art= (Document) a.get("artist");
            System.out.println(k+"."+a.getString("name")+" - "+art.get("name")+" ( auditeurs :"+a.get("listeners")+" - nombre d'ecoute:"+a.get("playcount")+")");
            k++;
        }

    }


    public static void getTop10A(String artiste){
        String url="http://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist="+artiste+"&api_key="+API_KEY+"&format=json&limit=10";
        HTTPTools httpTools = new HTTPTools();
        String jsonResponse = httpTools.sendGet(url);
        Document res = Document.parse(jsonResponse);
        Document albums=(Document) res.get("topalbums");
        ArrayList<Document> lalbum= (ArrayList<Document>) albums.get("album");
        int i=1;
        for(Document a :lalbum){
            Document art=(Document)a.get("artist");
            System.out.println(i+"."+a.getString("name")+" - "+art.get("name"));
            i++;
        }

    }



}
