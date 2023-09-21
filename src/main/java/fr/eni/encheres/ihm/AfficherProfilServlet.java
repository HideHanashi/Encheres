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

//		String pseudo = request.getParameter("pseudo");
//		String nom = request.getParameter("nom");
//		String prenom = request.getParameter("prenom");
//		String email = request.getParameter("email");
//		String telephone = request.getParameter("telephone");
//		String rue = request.getParameter("rue");
//		String code_postal = request.getParameter("code_postal");
//		String ville = request.getParameter("ville");
//
//		request.setAttribute("pseudo", pseudo);
//		request.setAttribute("nom", nom);
//		request.setAttribute("prenom", prenom);
//		request.setAttribute("email", email);
//		request.setAttribute("telephone", telephone);
//		request.setAttribute("rue", rue);
//		request.setAttribute("code_postal", code_postal);
//		request.setAttribute("ville", ville);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// récupérer le param dans url
			int id = Integer.parseInt(request.getParameter("id"));
			// supprimer un jeu
			UtilisateursManager.getInstance().removeUtilisateur(id);
			// redirect
			response.sendRedirect(request.getContextPath() + "");
		} catch (Exception e) {
			response.sendError(500);
		}

	}
}
