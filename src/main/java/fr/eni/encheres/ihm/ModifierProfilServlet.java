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

@WebServlet("/modifiermonprofil")
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
		int id = utilisateurSession.getNoUtilisateur();
		// récupérer le param dans url
		Utilisateur utilisateur = UtilisateursManager.getInstance().recupUtilisateur(id);

		// transmettre l'objet vers la jsp
		request.setAttribute("user", utilisateur);

		request.getRequestDispatcher("/WEB-INF/pages/modifier-mon-profil.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("user");
			int id = utilisateurSession.getNoUtilisateur();
			System.out.println(id);
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String motDePasse = request.getParameter("nouveauMotDePasse");

			Utilisateur utilisateur = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					motDePasse);
			UtilisateursManager.getInstance().modifyUtilisateur(utilisateur);
			response.sendRedirect(request.getContextPath() + "");
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}

}
