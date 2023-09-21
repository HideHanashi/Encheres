package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;

@WebServlet("/supprimermonprofil")
public class SupprimerProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// récupérer le param dans url
			int id = Integer.parseInt(request.getParameter("id"));
			// supprimer un jeu
			UtilisateursManager.getInstance().removeUtilisateur(id);
			// redirect
			response.sendRedirect(request.getContextPath() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
