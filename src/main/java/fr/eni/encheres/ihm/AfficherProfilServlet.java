package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bo.Utilisateur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/monprofil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();

			// récupérer le param dans url
			Utilisateur utilisateur = UtilisateursManager.getInstance().recupUtilisateur(id);

			// transmettre l'objet vers la jsp
			request.setAttribute("user", utilisateur);
			// forward
			request.getRequestDispatcher("/WEB-INF/pages/mon-profil.jsp").forward(request, response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
			response.sendError(404);
		}
	}

}
