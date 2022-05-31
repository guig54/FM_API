package tests;
import org.bson.Document;

import java.util.ArrayList;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoTest {

	public static void main(String[] args) {
		/*Document a = Test.getTrackInfo("caca");
		System.out.println(a);*/
		//MongoClient mongoClient = MongoClients.create("mongodb://<username>:<password>@<hostname>:<port>/?authSource=<authenticationDb>");
		String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("testAvantGo");
        try {
            Bson command = new BsonDocument("ping", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            System.out.println("Connected successfully to server.");
        } catch (MongoException me) {
            System.err.println("An error occurred while attempting to run a command: " + me);
        }
        MongoCollection<Document> collection = database.getCollection("utilisateurs");
        //find rend tout les resultats et faut faire .first() pour le premier et voir pour le reste
        System.out.println(collection.find());
      //db.getCollection("customers").find({});
        
        //comme ça on crée les query 
        Bson query= Filters.and(
                Filters.gt("id", 5)
        );
        System.out.println(collection.find(query).first());
        
        
        
        // montrer tout le res
        MongoCursor<Document> cursor = collection.find().iterator();
        
        while (cursor.hasNext()) {
        	System.out.println(cursor.next());
        }
        cursor.close();
        
    }
		

}
