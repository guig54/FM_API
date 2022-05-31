package main;

import org.bson.Document;

public class Ask {
	
	public static void getTag(String tag) {
		//BDD est dans la base ?
		//if (RequeteBDD.tagExist(tag)) {
			//SI oui get dans REqueteBDD.java
		//} else {
            RequeteAPI.getTag(tag);

            //ajouter res à la bdd
		//}
		
	}
	
}
