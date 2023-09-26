package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.jdbc.exception.JDBCException;

public interface UtilisateurDao {
	Utilisateur findByUsername(String pseudo);

	void save(Utilisateur user) throws JDBCException;

	void remove(int noUtilisateur);

	void modify(Utilisateur user) throws BLLException;
	
	void modifyPassword(Utilisateur user);

	void modify(Retrait retrait);

	Utilisateur findByEmail(String email);

	Utilisateur findOne(int noUtilisateur);

	List<Utilisateur> findAll();

	void modifyCredit(Utilisateur user);

}
