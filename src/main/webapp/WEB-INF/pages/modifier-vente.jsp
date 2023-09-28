<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>
	<div class="">
		<div class="row text-center">
			<h1>Modifier l'enchère : '${ article.nomArticle }'</h1>
		</div>
		<div class="container overflow-hidden text-center">
			<c:if test="${ ! empty error }">
				<div class="alert alert-danger">${ error }</div>
			</c:if>
			<form method="post">
				<div class="row gx-5">
					<div class="col">
						<div class="p-3">
							<label for="nomArticle" class="form-label">Article : </label> <input
								type="text" class="form-control" value="${ article.nomArticle }"
								name="nomArticle" id="nomArticle">
						</div>
						<div class="p-3 form-floating">
							<textarea class="form-control" placeholder="Leave a comment here"
								name="description" id="description"
								style="max-height: 168px; min-height: 168px;">${ article.description }</textarea>
							<label class="fw-lighter fst-italic mx-3 my-2" disabled
								for="description">Description de l'objet . . .</label>
						</div>
						<div class="p-3">
							<label for="categorie" class="form-label">Catégorie :</label> <select
								class="form-select" aria-label="Default select example"
								id="categorie" name="categorie">
								<option selected value="${ article.categorie.noCategorie }">${ article.categorie.libelle }</option>
								<c:forEach var="categories" items="${ categorie }">
									<option value="${ categories.noCategorie }">${ categories.libelle }</option>
								</c:forEach>
							</select>
						</div>
						<div class="p-3">
							<label for="img" class="form-label">Photo de l'article: </label>
							<div class="mb-3">
								<input class="form-control" type="file" id="img" name="img">
							</div>
						</div>
						<div class="p-3">
							<label for="miseAPrix" class="form-label">Mise à prix: </label>
							<div class="input-group">
								<input type="number" class="form-control" name="miseAPrix"
									id="miseAPrix" aria-label="Euro montant"
									value="${ article.miseAPrix }"> <span
									class="input-group-text"><i class="fa-solid fa-coins"></i></span>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="p-3">
							<label for="telephone" class="form-label">Début de
								l'enchère: </label> <input type="date" class="form-control"
								name="dateDebutEncheres" id="dateDebutEncheres"
								value="${ article.dateDebutEncheres }">
						</div>
						<div class="p-3">
							<label for="codePostal" class="form-label">Fin de
								l'enchère: </label> <input type="date" class="form-control"
								name="dateFinEncheres" id="dateFinEncheres"
								value="${ article.dateFinEncheres }">
						</div>
						<div class="card p-3" style="width: 30rem;">
							<div class="card-body">
								<h5 class="card-title">Lieu de Retrait</h5>
								<p class="fst-italic fw-lighter"
									style="font-size: 14px; color: rgb(140, 140, 140);">L'adresse
									de retrait est défini sur votre adresse de profil par défaut.</p>
								<div class="p-3">
									<label for="rue" class="form-label">Rue: </label> <input
										type="text" class="form-control" value="${ retrait.rue }"
										name="rue" id="rue">
								</div>
								<div class="p-3">
									<label for="codePostal" class="form-label">Code Postal:</label>
									<input type="text" class="form-control"
										value="${ retrait.codePostal }" name="codePostal"
										id="codePostal">
								</div>
								<div class="p-3">
									<label for="ville" class="form-label">Ville: </label> <input
										type="text" class="form-control" value="${ retrait.ville }"
										name="ville" id="ville">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="p-3 mt-4 pt-4">
					<button class="btn btn-primary" role="button" type="submit">Enregistrer</button>
					<a class="btn btn-secondary" role="button" type="reset"
						href="${ pageContext.request.contextPath }">Effacer</a>
				</div>
			</form>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>