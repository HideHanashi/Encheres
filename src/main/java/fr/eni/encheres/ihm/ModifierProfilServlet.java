package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifier-mon-profil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
					Utilisateur utilisateur =  UtilisateursManager.getInstance().recupUtilisateur(noUtilisateur);
					request.setAttribute("utilisateur", utilisateur);
					request.getRequestDispatcher("/WEB-INF/pages/modifier-mon-profil.jsp").forward(request, response);
				} catch (Exception e) {
					response.sendError(404);
				}				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");		
			Utilisateur utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
			UtilisateursManager.getInstance().modifyUtilisateur(utilisateur);
			response.sendRedirect( request.getContextPath() +"/mon-profil");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
