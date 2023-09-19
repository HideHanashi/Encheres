package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {

	private int noCategorie;
	private String libelle;
	
	private List<ArticleVendu> categorieArticle = new ArrayList<ArticleVendu>();


	
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	
	
	public List<ArticleVendu> getCategorieArticle() {
		return categorieArticle;
	}
	public void setCategorieArticle(List<ArticleVendu> categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", categorieArticle="
				+ categorieArticle + "]";
	}
	
}
