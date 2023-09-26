package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

public interface ArticlesDao {
	void save(ArticleVendu articleVendu);

	ArticleVendu findOne(int id);

	List<ArticleVendu> findAll();

	void modify(ArticleVendu articleVendu);

	void remove(int id);

	List<ArticleVendu> findArticleByCategorie(String categorie);

	List<ArticleVendu> findByName(String query);

	List<ArticleVendu> findByID(Integer idArticle);

	List<ArticleVendu> selectAll();

	void modifyCredit(ArticleVendu credit);
}
