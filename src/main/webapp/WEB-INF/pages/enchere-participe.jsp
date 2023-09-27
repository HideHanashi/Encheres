<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<main>
	<div class="p-3">
		<input type="radio" class="btn-check" name="options" id="option1" autocomplete="off" checked>
		<label class="btn btn-primary" for="option1">Enchères Remporté</label>
		
		<input type="radio" class="btn-check" name="options" id="option2" autocomplete="off">
		<label class="btn btn-primary" for="option2">Enchères Perdu</label>
		
		<c:if test="${ articleparticipe == true }">
			<p>True</p>
			<div class="row mt-5">
				<div class="container text-center containerarticles">
					<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
						<div class="col">
							<c:forEach var="article" items="${ articles }">
								<c:if
									test="${ article.utilisateur.noUtilisateur == user.noUtilisateur }">
		
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
		</c:if>
		<c:if test="${ articleparticipe == false }">
			<p>False</p>
			<div class="row mt-5">
				<div class="container text-center containerarticles">
					<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
						<div class="col">
							<c:forEach var="article" items="${ articles }">
								<c:if
									test="${ article.utilisateur.noUtilisateur == user.noUtilisateur }">
		
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
		</c:if>
	</div>
</main>

<%@ include file="/WEB-INF/fragments/footer.jspf"%>
