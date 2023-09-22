<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<div class="row mt-5">
		<div class="col-8 offset-2">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>Nom de l'Article</th>
						<th>Mise à prix</th>
						<th>Fin de l'enchère</th>
						<th>Vendeur</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="enchere" items="${ encheres }">
						<tr>
							<td>${ enchere.articleVendu.nomArticle }</td>
							<td>${ enchere.montantEnchere }</td>
							<td>${ enchere.articleVendu.dateFinEncheres }</td>
							<td>${ enchere.utilisateur.pseudo }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
