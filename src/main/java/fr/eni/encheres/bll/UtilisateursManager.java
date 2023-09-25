package fr.eni.encheres.bll;

import java.util.List;
import java.util.Random;

import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ForgetPassword;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DaoFactory;
import fr.eni.encheres.dal.UtilisateurDao;
import fr.eni.encheres.dal.jdbc.exception.JDBCException;
import fr.eni.encheres.helper.PasswordEncoder;

public class UtilisateursManager {

	// DÉBUT SINGLETON

	private static UtilisateursManager instance;

	private UtilisateursManager() {
	}

	public static UtilisateursManager getInstance() {
		if (instance == null)
			instance = new UtilisateursManager();
		return instance;
	}

	// FIN SINGLETON

	// DÉBUT LA LOGIQUE MÉTIER

	// BLL UTILISATEUR

	private UtilisateurDao utilisateurDao = DaoFactory.getUtilisateurDao();

	public Utilisateur recupUtilisateur(int noUtilisateur) {
		return utilisateurDao.findOne(noUtilisateur);
	}

	public List<Utilisateur> recupTousLesUtilisateurs() {
		return utilisateurDao.findAll();
	}

	private Random rd = new Random();

//	public List<Utilisateur> searchAllUtilisateur() {
//		return utilisateurDao.findAll();
//	}

	public void addUtilisateur(Utilisateur user) throws JDBCException {
		utilisateurDao.save(user);
	}

	public void modifyUtilisateur(Utilisateur utilisateur) throws BLLException {
		utilisateurDao.modify(utilisateur);
	}

	public void removeUtilisateur(int noUtilisateur) {
		utilisateurDao.remove(noUtilisateur);
		;
	}

	public Utilisateur searchUtilisateur(String query) {
		return utilisateurDao.findByUsername(query);
	}

	// BLL AUTRES

	public void inscription(Utilisateur utilisateur) throws JDBCException, BLLException {
		checkFields(utilisateur);
		utilisateur.setMotDePasse(PasswordEncoder.hashPassword(utilisateur.getMotDePasse() // PASSWORD NON HACHER
		));
		utilisateurDao.save(utilisateur);
	}

	private void checkFields(Utilisateur utilisateur) throws BLLException {
		if (utilisateur == null)
			throw new BLLException("User est null");

		if (utilisateur.getPseudo().isBlank())
			throw new BLLException("L'username est obligatoire !");
		if (utilisateur.getEmail().isBlank())
			throw new BLLException("L'e-mail est obligatoire !");

		// VÉRIFIE LA SYNTAXE DE L'EMAIL
		if (utilisateur.getMotDePasse().isBlank())
			throw new BLLException("Le mot de passe est obligatoire !");
		if (utilisateur.getMotDePasse().length() < 8 || utilisateur.getMotDePasse().length() > 35)
			throw new BLLException("La taille du mot de passe doit etre entre 8 et 35 caractères !");

	}

	public Utilisateur login(String email, String password) {
		Utilisateur utilisateur = utilisateurDao.findByEmail(email);
		if (utilisateur != null && utilisateur.getEmail().equals(email)
				&& PasswordEncoder.verifyPassword(password, utilisateur.getMotDePasse())) {
			return utilisateur;
		}
		return null;
	}

	public Utilisateur profil(int noUtilisateur) {
		Utilisateur utilisateur = utilisateurDao.findOne(noUtilisateur);
		if (utilisateur != null && utilisateur.getNoUtilisateur() == (noUtilisateur)) {
			return utilisateur;
		}
		return null;
	}

	public ForgetPassword checkEmail(String email) throws BLLException {

		Utilisateur utilisateur = utilisateurDao.findByEmail(email);
		if (utilisateur == null)
			throw new BLLException("Erreur: l'email n'existe pas");

		// GÉNÉRE LE CODE
		String code = rd.nextLong(1, 9999999999L) + "";
		ForgetPassword fp = new ForgetPassword(code, utilisateur);

		// SAVE
		DaoFactory.getForgetPasswordDao().save(fp);

		// ENVOIE LE MAIL
		// PAR SMS VIA API
		System.out.println(code);

		return fp;
	}

	public void resetPassword(String email, String code, String newPassword) throws BLLException {

		ForgetPassword fp = DaoFactory.getForgetPasswordDao().resetPassword(email);

		if (!fp.getCode().equals(code))
			throw new BLLException("Le code est érroné !");

		Utilisateur utilisateur = fp.getUser();

		utilisateur.setMotDePasse(PasswordEncoder.hashPassword(newPassword));

		utilisateurDao.modifyPassword(utilisateur);

	}

	// BLL ADRESSE

	public void modifyAdresse(Retrait adresse) {
		utilisateurDao.modify(adresse);
	}

	public void supprimerUtilisateur(int noUtilisateur) {
		utilisateurDao.remove(noUtilisateur);
	}

}
