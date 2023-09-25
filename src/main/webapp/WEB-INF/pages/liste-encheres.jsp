<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<div class="p-3">
		<label for="categorie" class="form-label">Catégorie : </label>
		<form class="d-flex ms-2" role="search">
			<select class="form-select" aria-label="Categorie" id="c" name="c"
				type="submit">
				<option class="fst-italic disabled" disabled selected>Choisir
					une catégorie . . .</option>
				<c:forEach var="categories" items="${ categorie }">
					<option value="${ categories.noCategorie }">${ categories.libelle }</option>
				</c:forEach>
			</select>
			<button class="btn btn-primary" role="button" type="submit">Chercher</button>
		</form>
	</div>
	<div class="row mt-5">
		<div class="container text-center containerarticles">
			<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
				<div class="col">
					<c:forEach var="article" items="${ articles }">
						<c:if test="${ article.dateDebutEncheres <= LocalDate.now() }">
							<div class="card p-3" style="width: 18rem;">
								<c:if test="${ image != null }">
									<img src="..." class="card-img-top imagearticle" href="#"
										alt="...">
								</c:if>
								<div class="card-body">
									<h5 class="card-title">${ article.nomArticle }</h5>
									<p class="card-text">${ article.utilisateur.enchere.montantEnchere }
										<i class="fa-solid fa-coins"></i>
									</p>
									<p class="card-text">Fini le : ${ article.dateFinEncheres }</p>
									<form method="get" class="mb-4">

										<p class="card-text">
											Par : <a id="user" type="submit"
												href="${ pageContext.request.contextPath }/autreprofil?user=${ article.utilisateur.noUtilisateur }">
												${ article.utilisateur.pseudo }</a>
										</p>

									</form>
									<a class="btn btn-primary" role="button" id="auction"
										href="${ pageContext.request.contextPath }/detailvente?auction=${ article.noArticle }">
										Voir plus</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>