package main;

public class Ask {
	
	public static boolean connexion(String pseudo, String mdp) {
		boolean success = false;
		
		return success;
	}
	
	public static boolean inscription(String pseudo, String mdp) {
		boolean success = false;
		
		return success;
	}
	
	public static void getTag(String tag) {
		//BDD est dans la base ?
		if (RequeteBDD.tagExist(tag)) {
			//SI oui get dans REqueteBDD.java
		} else {
			//SI non get dand REQUETEApi.java
		}
		
	}
	
}
