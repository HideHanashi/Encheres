package fr.eni.encheres.dal;

import fr.eni.encheres.dal.jdbc.ArticlesDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.EnchereDaoJdbcImpl;
import fr.eni.encheres.dal.jdbc.RetraitDaoJdbcImpl;
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
	
	public static RetraitDao getRetraitDao() {

		return new RetraitDaoJdbcImpl();
	}

	public static EnchereDao getEnchereDao() {

		return new EnchereDaoJdbcImpl();
	}

	public static ForgetPasswordDao getForgetPasswordDao() {
		return new ForgetPasswordDaoJdbcImpl();
	}
}
