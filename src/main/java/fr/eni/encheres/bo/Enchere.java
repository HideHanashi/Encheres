package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	private int montantEnchere;

	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;
	public Enchere() {

	}

	public Enchere(Utilisateur utilisateur, ArticleVendu articleVendu, LocalDate dateEnchere, int montantEnchere) {
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	


	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", articleVendu="
				+ articleVendu + ", utilisateur=" + utilisateur + "]";
	}

	public void setNoArticle(int parseInt, Object NoArticle) {
	}

}
