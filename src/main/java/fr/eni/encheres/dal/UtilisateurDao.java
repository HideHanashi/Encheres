package fr.eni.encheres.dal;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.jdbc.exception.JDBCException;

public interface UtilisateurDao {
	Utilisateur findByUsername(String pseudo);

	void save(Utilisateur user) throws JDBCException;

	void remove(int id);

	void modify(Utilisateur user) throws BLLException;

	void modify(Retrait retrait);

	Utilisateur findByEmail(String email);

}
