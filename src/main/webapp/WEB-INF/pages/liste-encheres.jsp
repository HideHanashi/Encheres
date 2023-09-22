<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<div class="p-3">
		<label for="categorie" class="form-label">Catégorie : </label> <select
			class="form-select" aria-label="Default select example"
			id="categorie" name="categorie">
			<option selected value="0">Toutes</option>

			<c:forEach var="categories" items="${ categorie }">
				<option value="${ categories.noCategorie }">${ categories.libelle }</option>
			</c:forEach>
		</select>
	</div>
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
