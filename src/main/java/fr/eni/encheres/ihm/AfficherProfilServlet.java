package fr.eni.encheres.ihm;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		   String message = (String) request.getSession().getAttribute("success");
			request.getSession().removeAttribute("success");
			request.setAttribute("success", message);
			request.getRequestDispatcher("/WEB-INF/pages/connexion.jsp").forward(request, response);
		   
	   }
	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String utilisateur = request.getParameter("utilisateur");
			String password = request.getParameter("password");
			
	  }
}

