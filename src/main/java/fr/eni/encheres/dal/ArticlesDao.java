package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticlesDao {
	void save(ArticleVendu articleVendu);

	ArticleVendu findOne(int id);

	List<ArticleVendu> findAll();

	void modify(ArticleVendu articleVendu);

	void remove(int id);

	List<ArticleVendu> findByName(String query);

	List<ArticleVendu> findByID(Integer idArticle);

	List<ArticleVendu> selectAll();
}
