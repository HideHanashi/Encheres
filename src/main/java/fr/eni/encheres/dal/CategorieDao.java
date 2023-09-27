package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;

public interface CategorieDao {

	public List<Categorie> findByCategorie();

	public Categorie findCategorieById(int id);

}
