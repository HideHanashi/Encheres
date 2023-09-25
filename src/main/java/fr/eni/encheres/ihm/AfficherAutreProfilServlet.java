package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/autreprofil")
public class AfficherAutreProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int otherId = Integer.parseInt(request.getParameter("user")); 
			Utilisateur otherUtilisateur = UtilisateursManager.getInstance().recupUtilisateur(otherId);
			
			request.setAttribute("otheruser", otherUtilisateur);
			request.getRequestDispatcher("/WEB-INF/pages/autre-profil.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/autre-profil.jsp").forward(request, response);
	}
}
