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

public class RequeteAPI {

	
	public final static String API_KEY = "7f3c4b6c3b001b8a97a4976cc72b5a3b";
	
	public static void getTag(String tag) {


        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022-projet");
        try {
            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            System.out.println("Connected successfully to server.");
        } catch (MongoException me) {
            System.err.println("An error occurred while attempting to run a command: " + me);
        }
        MongoCollection<Document> collection = database.getCollection("GLBGS_tag");






		Document res = null;
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
		
	}

}
