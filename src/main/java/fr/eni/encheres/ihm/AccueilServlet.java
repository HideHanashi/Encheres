package fr.eni.encheres.ihm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.encheres.bll.EncheresManager;
import fr.eni.encheres.bo.Enchere;

@WebServlet("")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Enchere> listEncheres = null;
		listEncheres = EncheresManager.getInstance().searchAllEncheres();
		if (request.getParameter("q") != null) {
			listEncheres = EncheresManager.getInstance().searchEnchere(request.getParameter("q"));
		} else {
			listEncheres = EncheresManager.getInstance().searchAllEncheres();
		}
		
		request.setAttribute("encheres", listEncheres);
		request.setAttribute("annee", LocalDate.now().getYear());

		request.getRequestDispatcher("/WEB-INF/pages/liste-encheres.jsp").forward(request, response);

	}

}
