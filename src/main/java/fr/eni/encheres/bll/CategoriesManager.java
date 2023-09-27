package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DaoFactory;
import fr.eni.encheres.dal.CategorieDao;

public class CategoriesManager {

	// DÉBUT SINGLETON

	private static CategoriesManager instance;

	public CategoriesManager() {
		categorieDao = DaoFactory.getCategorieDao();
	}

	public static CategoriesManager getInstance() {
		if (instance == null)
			instance = new CategoriesManager();
		return instance;
	}

	// FIN SINGLETON

	private CategorieDao categorieDao = DaoFactory.getCategorieDao();

	public List<Categorie> searchByCategories() {
		return categorieDao.findByCategorie();
	}

	public Categorie getCategorieById(int id) {
		return categorieDao.findCategorieById(id);
	}

}
