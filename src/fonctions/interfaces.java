package fonctions;

import main.Ask;

public class interfaces {
	
	public static void accueil() {
		
	}


	public static void connexion() {
		
	}

	public static void inscription() {
	
	}
	
	public static void choixTag() {
		System.out.println("Entrez le tag voulu :");
		String tag = fonctions.entreeString();
		System.out.println(Ask.getTag(tag));
		accueil();
	}
	
	public static void choixArtist() {
		
	}
}
