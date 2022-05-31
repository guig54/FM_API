package fonctions;

import java.security.MessageDigest;
import java.util.Scanner;

public class fonctions {
public static Scanner sc = new Scanner(System.in);
	
	//Recupere les entrees Int en evitant les erreurs
	public static int entreeInt() {
		boolean bon = false;
		while (!(bon)) {
			if (sc.hasNextInt()) {
				bon = true;
			} else {
				System.out.println("Mauvaise entree recommencez : ");
				sc.nextLine();
			}
		}
        return Integer.parseInt(sc.next());
	}
	
	
	//Recupere les entrees String en evitant les erreurs
	public static String entreeString() {
		boolean bon = false;
		while (!(bon)) {
			if (sc.hasNextLine()) {
				bon = true;
			} else {
				System.out.println("Mauvaise entree recommencez : ");
				sc.nextLine();
			}
		}
        return sc.next();
	}
	
	//Crypte le mot au format sha256
	public static String sha256(String mot) {
	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(mot.getBytes("UTF-8"));
	        StringBuilder hexString = new StringBuilder();
	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) {
	            	hexString.append('0');
	            }
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
}
