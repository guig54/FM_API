package main;




import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import objets.Album;
import objets.Track;

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

import objets.Tag;

import java.util.ArrayList;

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
            t = new Tag(doc.getString("name"), doc.getInteger("total"), doc.getInteger("reach"), wiki.getString("summary"), doc.getString("content"));
        } catch (Exception e) {
            exist = false;
        }

        return t;
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

    public static Album getAlbum(String album, String artiste) {
        System.out.println("BDD " + album);
        boolean exist = true;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("SD2022_projet");
        MongoCollection<Document> collection = database.getCollection("GLBGS_tag");
        Document doc = null;
        Album a = null;
        try {
            doc = collection.find(Filters.and(Filters.eq("name", album), Filters.eq("artist", artiste))).first();
            Document listetags = (Document) doc.get("tags");
            ArrayList<Document> tags = (ArrayList<Document>) listetags.get("tag");
            ArrayList<String> tagNames = new ArrayList<>();
            for (Document o : tags) {
                tagNames.add(o.getString("name"));
            }
            Document tracks = (Document) doc.get("tracks");
            ArrayList<Document> track = (ArrayList<Document>) tracks.get("track");
            ArrayList<Track> ltrack = new ArrayList<>();
            for (Document t : track) {
                int duree = 0;
                if (t.getInteger("duration") != null)
                    duree = t.getInteger("duration");

                String tname = t.getString("name");
                Document attr = (Document) t.get("@attr");
                int rank = attr.getInteger("rank");
                Document tartiste = (Document) t.get("artist");
                String nameArtiste = tartiste.getString("name");
                String mbid = tartiste.getString("mbid");
                Track trackf = new Track(tname, nameArtiste, mbid, duree, rank);
                ltrack.add(trackf);
            }
            a = new Album(doc.getString("artist"), doc.getString("mbid"), tagNames, doc.getString("name"), doc.getString("listeners"), doc.getString("playcount"), ltrack);
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