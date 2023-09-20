package fr.eni.encheres.dal;

import java.util.List;
import fr.eni.encheres.bo.Enchere;

public interface EnchereDao {

	void save(Enchere enchere);

	List<Enchere> findAll();

	void modify(Enchere enchere);

	void remove(int id);

//	void encherir(Enchere enchere);

}