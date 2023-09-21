package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;

@WebServlet("/supprimermonprofil")
public class SupprimerProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();
			// récupérer le param dans url
			UtilisateursManager.getInstance().removeUtilisateur(id);

			response.sendRedirect(request.getContextPath() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
