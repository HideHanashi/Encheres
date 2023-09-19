package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticlesDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.ForgetPasswordDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.UtilisateurDaoJdbcImpl;

public class DaoFactory {
	private DaoFactory() {
	}

	public static UtilisateurDao getUtilisateurDao() {

		return new UtilisateurDaoJdbcImpl();
	}

	public static ArticlesDao getArticlesDao() {

		return new ArticlesDaoJdbcImpl();
	}

	public static ForgetPasswordDao getForgetPasswordDao() {
		return new ForgetPasswordDaoJdbcImpl();
	}
}
