package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SupprimerProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int noUtilisateur = Integer.parseInt( request.getParameter("noUtilisateur")  );
			Utilisateur utilisateur = UtilisateursManager.getInstance().recupUtilisateur(noUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
			request.getRequestDispatcher("/WEB-INF/pages/supprimer-profil.jsp")
				   .forward(request, response);
		}catch (Exception e) {
			
			response.sendError(404);
		}		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int noUtilisateur = Integer.parseInt( request.getParameter("noUtilisateur")  );			
			UtilisateursManager.getInstance().supprimerUtilisateur(noUtilisateur);
			response.sendRedirect( request.getContextPath() +"/liste-encheres");
		}catch (Exception e) {
			response.sendError(404);
		}
	}

}
