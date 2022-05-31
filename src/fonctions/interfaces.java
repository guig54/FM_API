package fonctions;

import main.Ask;

public class interfaces {
	
	public static void accueil() {
		int choix = fonctions.entreeInt();
		switch(choix) {
		case 1:
			break;
		
		case 2:
			break;
			
		}
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
