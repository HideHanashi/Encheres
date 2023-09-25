package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDao {

	void save(Retrait retrait);
	
	Retrait findOne(int id);
	
}
