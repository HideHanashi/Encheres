package fr.eni.encheres.dal;

//import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDao {
	
	void saveEnchere(Enchere enchere);

//	List<Enchere> findByEncherir(String montantEnchere);

	void modifyEnchere(Enchere enchere);
	
	void removeEnchere(int id);
	
//	void encherir(Enchere enchere);
	
}
