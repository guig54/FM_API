package main;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;


import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;

import fonctions.fonctions;
import objets.*;

import java.util.ArrayList;

public class RequeteBDD {

    public final static String uri = "mongodb://localhost:27017";


    public static boolean connexion(String pseudo, String mdp) {
        boolean success = true;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_users");
        Document doc = null;
        try {
			doc = collection.find(Filters.and(Filters.eq("pseudo", pseudo),Filters.eq("mdp", fonctions.sha256(mdp)))).first();
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		if (doc == null) {
			success = false;
		}

        return success;
    }

    public static boolean inscription(String pseudo, String mdp) {
        boolean success = false;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_users");
        Document doc = null;
        try {
			doc = collection.find(Filters.eq("pseudo", pseudo)).first();
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		if (doc == null) {
			success = true;
	        try {
	        	Document client = new Document().append("pseudo", pseudo).append("mdp", fonctions.sha256(mdp));
	            InsertOneResult result = collection.insertOne(client);
	        } catch (Exception e) {
	        	System.out.println(e);
	            success = false;
	        }
			//creer compte
		}
        return success;
    }

    public static Tag getTag(String tag) {
        System.out.println("BDD " + tag);
        boolean exist = true;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_tag");
        Document doc = null;
        Tag t = null;
		try {
			doc = collection.find(Filters.eq("name", tag)).first();
			Document wiki = (Document) doc.get("wiki");
			t = new Tag(doc.getString("name"),doc.getInteger("total"),doc.getInteger("reach"),wiki.getString("summary"),doc.getString("content"));
		} catch (Exception e) {
			exist = false;
		}
		
		return t;
	}
	
	public static Artist getArtist(String artist) {
		boolean exist = true;
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_artist");
        Document doc = null;
        Artist a = null;
		try {
			doc = collection.find(Filters.eq("name", artist)).first();
			Document bio = (Document) doc.get("bio");
			Document sim = (Document) doc.get("similar");
			Document tags = (Document) doc.get("tags");
			a = new Artist(doc.getString("name"),(ArrayList<String>)sim.get("name"),doc.getInteger("ecoute"),doc.getInteger("fans"),(ArrayList<String>)tags.get("name"),
					bio.getString("dateBio"),bio.getString("summaryBio"),bio.getString("contentBio"));
			
		} catch (Exception e) {
			
			exist = false;
		}
		
		return a;
	}
	
	//insert
	
	
	public static boolean addTag(Document doc) {
		boolean success = true;
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_tag");
        try {
            InsertOneResult result = collection.insertOne(doc);
        } catch (Exception e) {
            success = false;
        }
        
		return success;
	}
	
	
	public static boolean addTag(Tag tag) {
		return addTag(new Document()
				.append("name", tag.getName())
				.append("total", tag.getTotal())
				.append("reach", tag.getReach())
				.append("wiki", new Document()
						.append("summary", tag.getSummary())
						.append("content", tag.getContent()))
				);
	}
	
	public static boolean addArtist(Artist artist) {
		Document artistDoc = new Document()
				.append("name", artist.getName())
				.append("ecoute", artist.getEcoute())
				.append("fans", artist.getFans())
				.append("bio", new Document()
						.append("dateBio", artist.getDateBio())
						.append("summaryBio", artist.getSummaryBio())
						.append("contentBio", artist.getContentBio())
				);
		Document sim = new Document();
		sim.append("name", artist.getSimilaires());
		Document tags = new Document();
		tags.append("name", artist.getTags());
		
		artistDoc.append("similar", sim);
		artistDoc.append("tags", tags);
		
		return addArtist(artistDoc);
	}
	
	public static boolean addArtist(Document doc) {
		boolean success = true;
		MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_artist");
        try {
        	InsertOneResult result = collection.insertOne(doc);
        } catch (Exception e) {
        	success = false;
        }
        
		return success;
	}


    public static Album getAlbum(String album, String artiste) {
        System.out.println("BDD " + album);
        boolean exist = true;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_album");
        Document doc = null;
        Album a = null;
        try {
            doc = collection.find(Filters.and(Filters.eq("name", album), Filters.eq("artist", artiste))).first();
            //if (doc){
                Document listetags = (Document) doc.get("tags");
                ArrayList<String> tagNames = (ArrayList<String>) listetags.get("name");

                Document tracks = (Document) doc.get("tracks");
                ArrayList<Document> track = (ArrayList<Document>) tracks.get("track");
                ArrayList<Track> ltrack = new ArrayList<>();
                for (Document t : track) {
                    int duree = 0;
                    if (t.getInteger("duration") != null)
                        duree = t.getInteger("duration");
                    String tname = t.getString("name");
                    int rank = t.getInteger("rank");
                    String nameArtiste = t.getString("name");

                    Track trackf = new Track(tname, nameArtiste, duree, rank);
                    ltrack.add(trackf);
                }
            a = new Album(doc.getString("artist"), doc.getString("mbid"), tagNames, doc.getString("name"), doc.getString("listeners"), doc.getString("playcount"), ltrack);

            //}
        } catch (Exception e) {
            exist = false;
        }

        return a;
    }

    public static boolean addAlbum(Document doc) {
        boolean success = true;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_album");
        try {
            collection.insertOne(doc);
        } catch (Exception e) {
            success = false;
        }

        return success;
    }

    public static boolean addAlbum(Album album) {
        Document albumDoc = (new Document()
                .append("artist", album.getArtist())
                .append("mbid", album.getMbid())
        );
        Document tags = new Document();
        tags.append("name", album.getTname());

        albumDoc.append("name", album.getName());
        Document tracks = new Document();
        ArrayList<Document> ltrack = new ArrayList<Document>();
        for (Track t : album.getTracks()) {
            ltrack.add(new Document()
                    .append("duration", t.getDuration())
                    .append("name", t.getName())
                    .append("rank", t.getRank())
            );
        }
        tracks.append("track", ltrack);
        albumDoc.append("listeners", album.getListeners());
        albumDoc.append("playcount", album.getPlaycount());

        albumDoc.append("tags", tags);
        albumDoc.append("tracks", tracks);

        return addAlbum(albumDoc);
    }

}
