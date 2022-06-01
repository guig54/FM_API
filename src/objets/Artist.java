package objets;

import java.util.ArrayList;

public class Artist {

	private String name;
	private ArrayList<String> similaires;
	private int ecoute;
	private int fans;
	private ArrayList<String> tags;
	private String dateBio, summaryBio, contentBio;
	
	public Artist(String name, ArrayList<String> similaires, int ecoute, int fans, ArrayList<String> tags,
			String dateBio, String summaryBio, String contentBio) {
		this.name = name;
		this.similaires = similaires;
		this.ecoute = ecoute;
		this.fans = fans;
		this.tags = tags;
		this.dateBio = dateBio;
		this.summaryBio = summaryBio;
		this.contentBio = contentBio;
	}
	
	
	public Artist(String name, ArrayList<String> similaires, String ecoute, String fans, ArrayList<String> tags,
			String dateBio, String summaryBio, String contentBio) {
		this.name = name;
		this.similaires = similaires;
		this.ecoute = Integer.parseInt(ecoute);
		this.fans = Integer.parseInt(fans);
		this.tags = tags;
		this.dateBio = dateBio;
		this.summaryBio = summaryBio;
		this.contentBio = contentBio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getSimilaires() {
		return similaires;
	}

	public void setSimilaires(ArrayList<String> similaires) {
		this.similaires = similaires;
	}

	public int getEcoute() {
		return ecoute;
	}

	public void setEcoute(int ecoute) {
		this.ecoute = ecoute;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getDateBio() {
		return dateBio;
	}

	public void setDateBio(String dateBio) {
		this.dateBio = dateBio;
	}

	public String getSummaryBio() {
		return summaryBio;
	}

	public void setSummaryBio(String summaryBio) {
		this.summaryBio = summaryBio;
	}

	public String getContentBio() {
		return contentBio;
	}

	public void setContentBio(String contentBio) {
		this.contentBio = contentBio;
	}

	@Override
	public String toString() {
		return "Nom : "+name+
				"\n Artistes similaires : "+similaires+
				"\n Nombre d'écoute : "+ecoute+
				"\n Nombre de fans : "+fans+
				"\n Tags de l'artiste : "+tags+
				"\n Biographie : \n"+
				"\n date : "+dateBio+
				"\n resume : "+summaryBio+
				"\n contenu : "+contentBio;
	}
	
	
	
	
}
