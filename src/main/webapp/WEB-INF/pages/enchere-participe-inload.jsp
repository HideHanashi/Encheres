<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>
	<div class="p-3">
		<form method="get">
			<div class="form-check">
				<input class="form-check-input" type="radio" name="enchere" id="inload" checked>
				<label class="form-check-label" for="inload">Enchères En Cours</label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="enchere" id="win">
				<label class="form-check-label" for="win">Enchères Gagné</label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="enchere" id="loose">
				<label class="form-check-label" for="loose">Enchères Perdu</label>
			</div>
			
			<button class="btn btn-primary" role="button" type="submit">Chercher</button>
		</form>
		<div class="row mt-5">
			<div class="container text-center containerarticles">
				<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
					<div class="col">
						<c:forEach var="article" items="${ articles }">
								<c:if test="${ encheres.utilisateur.noUtilisateur == user.noUtilisateur }">
									<div class="card p-3" style="width: 18rem;">
										<c:if test="${ image != null }">
											<img src="..." class="card-img-top imagearticle" href="#"
												alt="...">
										</c:if>
										<div class="card-body">
											<h5 class="card-title">${ article.nomArticle }</h5>
											<p class="card-text">${ article.miseAPrix }
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
											<a class="btn btn-primary" role="button"
												href="${ pageContext.request.contextPath }/modifiermesarticles?auction=${ article.noArticle }">Modifier</a>
										</div>
									</div>
								</c:if>
							</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
