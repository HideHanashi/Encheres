package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import fr.eni.encheres.bll.ArticlesManager;
import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/detailvente")
public class DetailVenteServlet extends HttpServlet {
	Utilisateur util = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			if (utilisateurSession != null) {
				int idUser = utilisateurSession.getNoUtilisateur();
				Utilisateur utilisateur = UtilisateursManager.getInstance().recupUtilisateur(idUser);
				request.setAttribute("utilisateur", utilisateur);
			}
			
			int idArticle = Integer.parseInt(request.getParameter("auction"));
			Retrait retrait = RetraitManager.getInstance().recupRetrait(idArticle);
			ArticleVendu article = ArticlesManager.getInstance().recupArticle(idArticle);
			Enchere prixArticle = EncheresManager.getInstance().selectPrixArticle(idArticle);
			
			request.setAttribute("enchere", prixArticle);
			request.setAttribute("article", article);
			request.setAttribute("retrait", retrait);
			request.getRequestDispatcher("/WEB-INF/pages/details-vente.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// INTÉGRATION DES VARIABLES DE L'UTILISATEUR DANS LES VARIABLES BACK
			ArticleVendu idArticle = ArticlesManager.getInstance().recupArticle(Integer.parseInt(request.getParameter("auction")));
			int idArticleInt = idArticle.getNoArticle();
			int prixArticle = Integer.parseInt(request.getParameter("prixactuel"));
			int creditEncherir = Integer.parseInt(request.getParameter("encherir"));
			
			if (creditEncherir > prixArticle) {
				// RÉCUPÉRATION DE LA SESSION UTILISATEUR
				HttpSession session = request.getSession();
				Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
				int idUser = utilisateurSession.getNoUtilisateur();
				
				// RÉCUPÉRATION DE LA DATE
				LocalDate localDate = LocalDate.now();
				
				// RÉCUPÉRATION ET TRANSFORMATION DES CRÉDITS
				int creditUser = utilisateurSession.getCredit();
				int credit = creditUser - creditEncherir;
				
				// CRÉATION / UPDATE D'UNE ENCHÈRE
				Enchere enchereCredit = new Enchere(utilisateurSession, idArticle, localDate, creditEncherir);
				System.out.println("Coucou toi");
				if (enchereCredit.getUtilisateur().getNoUtilisateur() != idUser &&
						enchereCredit.getArticleVendu().getNoArticle() != idArticleInt) {
					System.out.println("Création enchère");
					EncheresManager.getInstance().addEnchere(enchereCredit);
				} else {
					System.out.println("Update enchère");
					EncheresManager.getInstance().modifyEnchere(enchereCredit);
				}
				
				// UPDATE DES CRÉDITS DE L'UTILISATEUR
				Utilisateur utilisateurCredit = new Utilisateur(idUser, credit);
				UtilisateursManager.getInstance().modifyCredit(utilisateurCredit); 
				
				
			} else {
				BLLException e = new BLLException("Vous devez mettre une valeur supérieur à la meilleur offre actuel !");
				request.setAttribute("error", e.getMessage());
				doGet(request, response);
				e.printStackTrace();
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}
}
