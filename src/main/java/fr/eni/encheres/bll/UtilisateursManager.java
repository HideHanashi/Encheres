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

	// DÉBUT DU SINGLETON

	private static UtilisateursManager instance;

	private UtilisateursManager() {
	}

	// Méthode pour obtenir l'instance unique de la classe
	public static UtilisateursManager getInstance() {
		if (instance == null)
			instance = new UtilisateursManager();
		return instance;
	}

	// FIN DU SINGLETON

	// DÉBUT DE LA LOGIQUE MÉTIER

	// BLL UTILISATEUR

	// Récupère un utilisateur par son numéro d'identification
	public Utilisateur recupUtilisateur(int noUtilisateur) {
		return utilisateurDao.findOne(noUtilisateur);
	}

	// Récupère tous les utilisateurs
	public List<Utilisateur> recupTousLesUtilisateurs() {
		return utilisateurDao.findAll();
	}

	// Générateur de nombres aléatoires
	private Random rd = new Random();

	// Ajoute un nouvel utilisateur
	public void addUtilisateur(Utilisateur user) throws JDBCException {
		utilisateurDao.save(user);
	}

	// Modifie les informations d'un utilisateur
	public void modifyUtilisateur(Utilisateur utilisateur) throws BLLException {
		utilisateurDao.modify(utilisateur);
	}

	// Supprime un utilisateur par son numéro d'identification
	public void removeUtilisateur(int noUtilisateur) {
		utilisateurDao.remove(noUtilisateur);
	}

	// Recherche un utilisateur par son pseudo ou son email
	public Utilisateur searchUtilisateur(String query) {
		return utilisateurDao.findByUsername(query);
	}
	
	// Modifie le crédit d'un utilisateur
	public void modifyCredit(Utilisateur user) {
		utilisateurDao.modifyCredit(user);
	}

	// BLL AUTRES

	// Méthode d'inscription d'un nouvel utilisateur
	public void inscription(Utilisateur utilisateur) throws JDBCException, BLLException {
		checkFields(utilisateur);
		// Hachage du mot de passe avant de le stocker
		utilisateur.setMotDePasse(PasswordEncoder.hashPassword(utilisateur.getMotDePasse()));
		utilisateurDao.save(utilisateur);
	}

	// Vérifie les champs obligatoires lors de l'inscription
	private void checkFields(Utilisateur utilisateur) throws BLLException {
		if (utilisateur == null)
			throw new BLLException("User est null");

		if (utilisateur.getPseudo().isBlank())
			throw new BLLException("L'username est obligatoire !");
		if (utilisateur.getEmail().isBlank())
			throw new BLLException("L'e-mail est obligatoire !");

		// Vérifie la syntaxe du mot de passe
		if (utilisateur.getMotDePasse().isBlank())
			throw new BLLException("Le mot de passe est obligatoire !");
		if (utilisateur.getMotDePasse().length() < 8 || utilisateur.getMotDePasse().length() > 35)
			throw new BLLException("La taille du mot de passe doit être entre 8 et 35 caractères !");
	}

	// Méthode de connexion d'un utilisateur
	public Utilisateur login(String email, String password) {
		Utilisateur utilisateur = utilisateurDao.findByEmail(email);
		if (utilisateur != null && utilisateur.getEmail().equals(email)
				&& PasswordEncoder.verifyPassword(password, utilisateur.getMotDePasse())) {
			return utilisateur;
		}
		return null;
	}

	// Récupère les informations d'un utilisateur par son numéro d'identification
	public Utilisateur profil(int noUtilisateur) {
		Utilisateur utilisateur = utilisateurDao.findOne(noUtilisateur);
		if (utilisateur != null && utilisateur.getNoUtilisateur() == (noUtilisateur)) {
			return utilisateur;
		}
		return null;
	}

	// Vérifie l'existence d'un email et génère un code pour la réinitialisation du mot de passe
	public ForgetPassword checkEmail(String email) throws BLLException {

		Utilisateur utilisateur = utilisateurDao.findByEmail(email);
		if (utilisateur == null)
			throw new BLLException("Erreur: l'email n'existe pas");

		// Génère le code
		String code = rd.nextLong(1, 9999999999L) + "";
		ForgetPassword fp = new ForgetPassword(code, utilisateur);

		// Enregistre le code
		DaoFactory.getForgetPasswordDao().save(fp);

		// Envoie le code par SMS via une API
		System.out.println(code);

		return fp;
	}

	// Réinitialise le mot de passe avec un nouveau mot de passe
	public void resetPassword(String email, String code, String newPassword) throws BLLException {

		ForgetPassword fp = DaoFactory.getForgetPasswordDao().resetPassword(email);

		if (!fp.getCode().equals(code))
			throw new BLLException("Le code est erroné !");

		Utilisateur utilisateur = fp.getUser();

		utilisateur.setMotDePasse(PasswordEncoder.hashPassword(newPassword));

		utilisateurDao.modifyPassword(utilisateur);
	}

	// BLL ADRESSE

	// Modifie l'adresse d'un utilisateur
	public void modifyAdresse(Retrait adresse) {
		utilisateurDao.modify(adresse);
	}

	// Supprime un utilisateur par son numéro d'identification
	public void supprimerUtilisateur(int noUtilisateur) {
		utilisateurDao.remove(noUtilisateur);
	}

}
