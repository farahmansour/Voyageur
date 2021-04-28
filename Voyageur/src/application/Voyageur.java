package application;

public class Voyageur
{
	private String prenom;
	private String nom;
	private String destination;
	private String raison;
	private Double age;
	private Double numvol;

	//constructeur vide
	public Voyageur()
	{
		this(null,null,null);
	}
	//constructeur avec 3 paramètres
	public Voyageur(String prenom, String nom, String destination)
	{
		this.prenom=prenom;
		this.nom=nom;
		this.destination=destination;
		this.raison="";
		this.age=0.0;
		this.numvol=0.0;
	}
	// Getters et Setters
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getRaison() {
	return raison;
}
public void setRaison(String raison) {
	this.raison = raison;
}
public Double getAge() {
	return age;
}
public void setAge(Double age) {
	this.age = age;
}
public Double getNumvol() {
	return numvol;
}
public void setNumvol(Double numvol) {
	this.numvol = numvol;
}


}
