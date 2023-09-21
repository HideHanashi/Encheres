package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DaoFactory;
import fr.eni.encheres.dal.RetraitDao;

public class ReatraitManager {
	// DÉBUT SINGLETON

	private static ReatraitManager instance;

	private ReatraitManager() {
	}

	public static ReatraitManager getInstance() {
		if (instance == null)
			instance = new ReatraitManager();
		return instance;
	}

	// FIN SINGLETON
	
	private RetraitDao retraitDao = DaoFactory.getRetraitDao();
	
	public void addRetrait(Retrait retrait) {
		retraitDao.save(retrait);
	}
}
