package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
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

	public void modifyArticle(ArticleVendu article) {
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

	// BLL VENTE & ACHAT

	// public List<ArticleVendu> listVente() {
	// return ArticleDao.findAllVente();
	// }

	// public List<ArticleVendu> listAchat() {
	// return ArticleDao.findAllAchat();
	// }
	
	private void isValid(ArticleVendu article) throws BLLException {
		if(article == null) throw new BLLException("L'article est vide. ");
		if(article.getNomArticle() == null || article.getNomArticle().isBlank()) throw new BLLException("Le nom de l'article est obligatoire.");
		if(article.getDescription() == null || article.getDescription().isBlank()) throw new BLLException("La description de l'article est obligatoire.");
		if(article.getDateDebutEncheres() == null) throw new BLLException("La date de début de l'enchère est obligatoire.");
		if(article.getDateFinEncheres() == null) throw new BLLException("La date de fin de l'enchère est obligatoire.");
		if(article.getCategorie() == null) throw new BLLException("Une catégorie pour l'article est obligatoire.");
		if(article.getMiseAPrix() < 0) throw new BLLException("Le prix pour l'article doit être supérieur à 0.");
	}
	
}
