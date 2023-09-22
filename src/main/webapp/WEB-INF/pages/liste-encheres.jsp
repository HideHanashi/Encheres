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
		<div class="container text-center containerarticles">
			<c:forEach var="enchere" items="${ encheres }">
				<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
					<div class="col">
						<div class="card p-3" style="width: 18rem;">
							<c:if test = "${ image != null }">
								<img src="..." class="card-img-top imagearticle" href="#" alt="...">
							</c:if>
							<c:if test = "${ enchere.articleVendu.dateDebutEncheres != LocalDate.now() }">
							<div class="card-body">
								<h5 class="card-title">${ enchere.articleVendu.nomArticle }</h5>
								<p class="card-text">${ enchere.montantEnchere } <i class="fa-solid fa-coins"></i></p>
								<p class="card-text">Fini le : ${ enchere.articleVendu.dateFinEncheres }</p>
								<p class="card-text">Par : ${ enchere.utilisateur.pseudo }</p>
								<a href="#">Voir plus</a>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>