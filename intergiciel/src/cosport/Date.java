package cosport;

public class Date {
	
	private int annee;
	private int mois;
	private int jour;
	private int heure;
	private int minute;
	
	public Date(int a, int m, int j, int h) {
		annee = a;
		mois = m % 12;
		jour = j % 31;
		heure = h % 24;
		minute = 0;
	}
	
	public Date(int a, int m, int j, int h, int min) {
		this(a,m,j,h);
		minute = min % 60;
	}

	
	
	
	
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee ;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois % 12;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour %31;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure % 24;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute % 60;
	}
	
	public String afficher() {
		String s = jour + "/" + mois + "/" + annee;
		return s;
	}
	
	public String afficherTemps() {
		String s = heure + "h" + minute + "min";
		return s;
	}
	
	public String toString() {
		String s = this.afficher() + " a " + this.afficherTemps();
		return s;
	}
	

	
}
