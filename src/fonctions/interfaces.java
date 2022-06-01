package fonctions;

import java.util.Scanner;

import main.Ask;
import main.RequeteAPI;
import main.RequeteBDD;
import objets.*;


public class interfaces {
	
	public static Scanner pause = new Scanner(System.in);
	
	public static void accueil() {
		System.out.println("Bonjour bienvenue sur Musique-Star !\n1.Connexion\n2.Inscription");
		int choix = fonctions.entreeInt();
		switch(choix) {
		case 1:
			connexion();
			break;
		
		case 2:
			inscription();
			break;
			
		default:
			accueil();
			
		}
	}
	
	public static void hub() {
		System.out.println("Que voulez vous faire : "
				+ "\n1.Infos sur un genre (tag)"
				+ "\n2.Infos sur un artiste"
				+ "\n3.Infos sur l'album d'un artiste"
				+ "\n4.Top 10 Monde (Artistes, tags ,albums)"
				+ "\n5.Top 10 d'un pays (Artistes, tags ,albums)");
		int choix = fonctions.entreeInt();
		switch(choix) {
		case 1:
			tag();
			break;
		case 2:
			artist();
			break;
		case 3:
			album();
			break;
		case 4:
			RequeteAPI.getTop10Monde();
			pause_hub();
			break;
		case 5:
			topPays();
			break;
		default:
			hub();
		}
		
	}
	
	public static void topPays() {
		System.out.println("Quel pays ? : ");
		String choix = pause.nextLine();
		RequeteAPI.getTop10Geo(choix);
		pause_hub();
	}
	
	public static void album() {
		System.out.println("Quel artiste ? :");
		String choix = pause.nextLine();
		System.out.println("Quel album ? :");
		String album = pause.nextLine();
		System.out.println("Informations sur cette album : \n"+Ask.getAlbum(album, choix));
		pause_hub();
		
	}
	
	public static void artist() {
		System.out.println("Quel artiste ? :");
		String choix = pause.nextLine();
		System.out.println("Informations sur ce tag : \n"+Ask.getArtist(choix));
		pause_hub();
		
	}
	
	public static void tag() {
		System.out.println("Quel genre ? :");
		String choix = pause.nextLine();
		System.out.println("Informations sur ce tag : \n"+Ask.getTag(choix));
		pause_hub();
		
	}


	public static void connexion() {
		System.out.println("pseudo : ");
		String pseudo = pause.nextLine();
		System.out.println("mot de passe : ");
		String mdp = pause.nextLine();
		System.out.println();
		if (RequeteBDD.connexion(pseudo, mdp)) {
			System.out.println("Heureux de vous revoir "+pseudo+" !");
			hub();
		} else {
			System.out.println("Identifiants incorrects, retour à l'accueil...");
			accueil();
		}
	}

	public static void inscription() {
		System.out.println("pseudo : ");
		String pseudo = pause.nextLine();
		System.out.println("mot de passe : ");
		String mdp = pause.nextLine();
		System.out.println();
		if (RequeteBDD.inscription(pseudo, mdp)) {
			System.out.println("Bienvenue "+pseudo+" !");
			hub();
		} else {
			System.out.println("Pseudo déjà utilisé retour à l'accueil...");
			accueil();
		}
	}
	
	public static void choixTag() {
		System.out.println("Entrez le tag voulu :");
		String tag = fonctions.entreeString();
		System.out.println(Ask.getTag(tag));
		pause_hub();
	}
	
	public static void choixArtist() {
		
	}
	
	
	public static void pause_hub() {
		System.out.println("\n\nAppuyez sur entrer pour revenir au menu...\n");
		pause.nextLine();
		hub();
	}
}
