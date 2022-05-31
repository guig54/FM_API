package fonctions;

import java.util.Scanner;

import main.Ask;
import main.RequeteBDD;


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
				+ "\n2."
				+ "\n3."
				+ "\n4."
				+ "\n5.");
		
	}
	
	public static void tag() {
		
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
		System.out.println("Appuyez sur entrer pour revenir au menu...");
		pause.nextLine();
		hub();
	}
}
