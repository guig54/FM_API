package main;

import java.util.ArrayList;

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

public class RequeteBDD {
	
	public final static String uri = "mongodb://localhost:27017";
	
	
	public static boolean connexion(String pseudo, String mdp) {
		boolean success = false;
		
		return success;
	}
	
	public static boolean inscription(String pseudo, String mdp) {
		boolean success = false;
		
		return success;
	}

	public static Tag getTag(String tag) {
		System.out.println("BDD "+tag);
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
		System.out.println("BDD "+artist);
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
}
