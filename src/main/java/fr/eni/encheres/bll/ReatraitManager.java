package fr.eni.encheres.bll;

import fr.eni.encheres.bll.exception.BLLException;
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
	
	public void addRetrait(Retrait retrait) throws BLLException {
		isValid(retrait);
		retraitDao.save(retrait);
	}
	
	private void isValid(Retrait retrait) throws BLLException {
		if(retrait == null) throw new BLLException("L'article est vide. ");
		if(retrait.getRue() == null || retrait.getRue().isBlank()) throw new BLLException("La rue pour le lieux de retrait de l'article est obligatoire.");
		if(retrait.getCodePostal() == null || retrait.getCodePostal().isBlank()) throw new BLLException("Le code postal pour le lieux de retrait de l'article est obligatoire.");
		if(retrait.getVille() == null || retrait.getVille().isBlank()) throw new BLLException("La ville pour le lieux de retrait de l'article est obligatoire.");
	}
}
