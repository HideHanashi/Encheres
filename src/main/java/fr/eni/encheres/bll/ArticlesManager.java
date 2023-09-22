package fr.eni.encheres.bll;

import java.util.List;

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

	public void addArticle(ArticleVendu article) {
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
}
