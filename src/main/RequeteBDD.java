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

public class RequeteBDD {


    public MongoCollection<Document> getTag(){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022-projet");
        try {
            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            System.out.println("Connected successfully to server.");
        } catch (
                MongoException me) {
            System.err.println("An error occurred while attempting to run a command: " + me);
        }
        MongoCollection<Document> collection = database.getCollection("GLBGS_tag");
        return collection;
    }

	public static boolean tagExist(String tag) {
		boolean exist = false;
		//voir si existe
		return exist;
	}

    public static void addTag(Document res) {
        BasicDBObject document = res;
        document.put("name", "Shubham");
        document.put("company", "Baeldung");
        collection.insert(document);
    }
}
