package main;
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

import fonctions.fonctions;
import fonctions.interfaces;

public class Principale {

	public static void main(String[] args) {
<<<<<<< HEAD
		RequeteBDD.inscription("root", "root");
        interfaces.accueil();
		//System.out.println(Ask.getArtist("booba"));
        //Ask.getAlbum("ULTRA","Booba");
=======

        //interfaces.accueil();
        // System.out.println(Ask.getArtist("booba"));
        // Ask.getAlbum("ULTRA","Booba");
        //RequeteAPI.getTop10Global();
        //RequeteAPI.getTop10Geo("france");
        RequeteAPI.getTop10Monde();


>>>>>>> 462f19453c2620c95d7c5d9cbf324c4b9b8d6298

    }
		

}
