package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ArticlesDao;
import fr.eni.encheres.dal.DaoFactory;

public class ArticlesManager {

	// DÉBUT SINGLETON

	private static ArticlesManager instance;

	public ArticlesManager() {
		articleDao = DaoFactory.getArticlesDao();
	}

	public static ArticlesManager getInstance() {
		if (instance == null)
			instance = new ArticlesManager();
		return instance;
	}

	// FIN SINGLETON

	private ArticlesDao articleDao = DaoFactory.getArticlesDao();

	// DÉBUT LA LOGIQUE MÉTIER

	// BLL ARTICLES

//	public ArticleVendu findArticle(int id) {
//		return articleDao.findOne(id);
//	}

	public List<ArticleVendu> searchAllArticle() {
		return articleDao.findAll();
	}

	public void addArticle(ArticleVendu article) throws BLLException {
		isValid(article);
		articleDao.save(article);
	}

	public void modifyArticle(ArticleVendu article) throws BLLException {
		isValid(article);
		articleDao.modify(article);
	}

	public void deleteArticle(int id) {
		articleDao.remove(id);
	}

	public List<ArticleVendu> searchArticle(String query) {
		return articleDao.findByName(query);
	}

	public ArticleVendu recupArticle(int id) {
		return articleDao.findOne(id);
	}

	public List<ArticleVendu> searchCategorie(String categorie) {
		return articleDao.findArticleByCategorie(categorie);
	}

	// BLL VENTE & ACHAT

	// public List<ArticleVendu> listVente() {
	// return ArticleDao.findAllVente();
	// }

	// public List<ArticleVendu> listAchat() {
	// return ArticleDao.findAllAchat();
	// }

	private void isValid(ArticleVendu article) throws BLLException {
		if (article == null)
			throw new BLLException("L'article est vide.");
		if (article.getNomArticle() == null || article.getNomArticle().isBlank())
			throw new BLLException("Le nom de l'article est obligatoire.");
		if (article.getDescription() == null || article.getDescription().isBlank())
			throw new BLLException("La description de l'article est obligatoire.");
		if (article.getDateDebutEncheres() == null)
			throw new BLLException("La date de début de l'enchère est obligatoire.");
		if (article.getDateDebutEncheres().isBefore(LocalDate.now()))
			throw new BLLException("La date de début doit être supérieur ou égale à la date d'aujourd'hui.");
		if (article.getDateFinEncheres() == null)
			throw new BLLException("La date de fin de l'enchère est obligatoire.");
		if (!article.getDateFinEncheres().isAfter(LocalDate.now()))
			throw new BLLException("La date de fin doit être supérieur à la date d'aujourd'hui.");
		if (article.getCategorie().getNoCategorie() <= 0)
			throw new BLLException("Une catégorie pour l'article est obligatoire.");
		if (article.getMiseAPrix() <= 0)
			throw new BLLException("Le prix pour l'article doit être supérieur à 0.");
	}

	public List<ArticleVendu> chooseArticle(Integer noArticle, int noUtilisateur) {
		return articleDao.findByID(noArticle);
	}

	public List<ArticleVendu> affichageArticles(String categorie, String motCle) throws BLLException {
		List<ArticleVendu> listeArticles = new ArrayList<>();
		listeArticles = articleDao.selectAll();
		return listeArticles;
	}

	public void modifyCredit(ArticleVendu credit) {
		articleDao.modifyCredit(credit);
	}
}
