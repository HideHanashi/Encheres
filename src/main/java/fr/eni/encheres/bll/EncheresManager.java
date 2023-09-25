package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DaoFactory;
import fr.eni.encheres.dal.EnchereDao;

public class EncheresManager {

	private static EncheresManager instance;

	public EncheresManager() {
	}

	public static EncheresManager getInstance() {
		if (instance == null)
			instance = new EncheresManager();
		return instance;
	}

	// FIN SINGLETON

	private EnchereDao enchereDao = DaoFactory.getEnchereDao();

	public List<Enchere> searchAllEncheres() {
		return enchereDao.findAll();
	}

	public void addEnchere(Enchere enchere) {
		enchereDao.save(enchere);
	}

	public List<Enchere> searchEnchere(String query) {
		return enchereDao.findByName(query);
	}
	
	public List<Enchere> searchCategorie(String categorie) {
		return enchereDao.findArticleByCategorie(categorie);
	}

	public void modifyEnchere(Enchere enchere) {
		enchereDao.modify(enchere);
	}

	public void deleteEnchere(int id) {
		enchereDao.remove(id);
	}

}
