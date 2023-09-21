package fr.eni.encheres.ihm;

import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.jdbc.exception.JDBCException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/creation-compte.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Utilisateur user = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"),
					request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
					request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"),
					request.getParameter("mdp"));
			// user.setConfirmPassword()

			UtilisateursManager.getInstance().inscription(user);
			// Flash
			request.getSession().setAttribute("success", "Le compte a bien été créer!");
			response.sendRedirect(request.getContextPath() + "/connexion");
		} catch (JDBCException | BLLException e) {

			request.setAttribute("error", e.getMessage());
			doGet(request, response);
			e.printStackTrace();
		}
	}

}
