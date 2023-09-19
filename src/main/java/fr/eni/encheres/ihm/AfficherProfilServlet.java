package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		   String pseudo = request.getParameter("pseudo");
		   String nom = request.getParameter("nom");
		   String prenom = request.getParameter("prenom");
		   String email = request.getParameter("email");
		   String telephone = request.getParameter("telephone");
		   String rue = request.getParameter("rue");
		   String code_postal = request.getParameter("code_postal");
		   String ville = request.getParameter("ville");
		   String mot_de_passe = request.getParameter("mot_de_passe");
		   String credit = request.getParameter("credit");
		   String administrateur = request.getParameter("administrateur");
		   
		   request.setAttribute("pseudo", pseudo);
	       request.setAttribute("nom", nom);
	       request.setAttribute("prenom", prenom);
	       request.setAttribute("email", email);
	       request.setAttribute("telephone", telephone);
	       request.setAttribute("rue", rue);
	       request.setAttribute("code_postal", code_postal);
	       request.setAttribute("ville", ville);
	       request.setAttribute("mot_de_passe", mot_de_passe);
	       request.setAttribute("credit", credit);
	       request.setAttribute("administrateur", administrateur);
	       
	       Utilisateur utilisateur = UtilisateursManager.getInstance().login(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur);
	        
	       request.getRequestDispatcher("/WEB-INF/pages/autre-profil.jsp").forward(request, response);
	   }
}

