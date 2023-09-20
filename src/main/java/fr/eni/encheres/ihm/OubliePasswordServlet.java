package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.encheres.bll.UtilisateursManager;
import fr.eni.encheres.bll.exception.BLLException;
import fr.eni.encheres.bo.ForgetPassword;

@WebServlet("/oubliepassword")
public class OubliePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/pages/mdp-oublie.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String email = request.getParameter("email");
			ForgetPassword fp = UtilisateursManager.getInstance().checkEmail(email);
			response.sendRedirect(request.getContextPath() + "/changepassword");
		} catch (BLLException e) {

			e.printStackTrace();
		}

	}

}
