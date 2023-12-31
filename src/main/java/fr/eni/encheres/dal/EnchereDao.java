package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Enchere;

public interface EnchereDao {

	void save(Enchere enchere);

	List<Enchere> findAll();

	void modify(Enchere enchere);

	void remove(int id);

	List<Enchere> findByName(String query);

	Enchere findOne(int id);

	Enchere selectPrixArticle(int id);

	boolean verifyIdAll(int idUser, int idArticle);

	List<Enchere> findAllParticipe(int id);
	
	List<Enchere> findAllParticipeWin(int id);

//	void encherir(Enchere enchere);

}
