<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>
<c:set var="today" value="<%= LocalDate.now() %>"/>
<main>
	<div class="p-3">
		<div class="form-check">
			<a class="btn btn-primary" role="button" id="auction"
				href="${ pageContext.request.contextPath }/articleparticipe">
				Enchères En Cours</a>
			<a class="btn btn-primary" role="button" id="auction"
				href="${ pageContext.request.contextPath }/articleparticipewin">
				Enchères Gagné</a>
			<a class="btn btn-primary" role="button" id="auction"
				href="${ pageContext.request.contextPath }/articleparticipeloose">
				Enchères Perdu</a>
		</div>
		<div class="row mt-5">
			<div class="container text-center containerarticles">
				<div class="row row-cols-2 row-cols-lg-5 g-2 g-lg-3">
					<div>
						<h1 class="mb-3">▌ Enchères Gagné </h1>
					</div>
						<div class="col">
							<c:forEach var="enchere" items="${ encheres }">
								<c:if test="${ enchere.articleVendu.dateFinEncheres < today }">
									<div class="card p-3" style="width: 18rem;">
										<c:if test="${ image != null }">
											<img src="..." class="card-img-top imagearticle" href="#"
												alt="...">
										</c:if>
										<div class="card-body">
											<h5 class="card-title">${ enchere.articleVendu.nomArticle }</h5>
											<p class="card-text">${ enchere.articleVendu.miseAPrix }
												<i class="fa-solid fa-coins"></i>
											</p>
											<p class="card-text">Fini le : ${ enchere.articleVendu.dateFinEncheres }</p>
											<form method="get" class="mb-4">
		
												<p class="card-text">
													Par : <a id="user" type="submit"
														href="${ pageContext.request.contextPath }/autreprofil?user=${ enchere.articleVendu.utilisateur.noUtilisateur }">
														${ enchere.articleVendu.utilisateur.pseudo }</a>
												</p>
		
											</form>
											<a class="btn btn-primary" role="button" id="auction"
												href="${ pageContext.request.contextPath }/autreprofil?user=${ enchere.articleVendu.utilisateur.noUtilisateur }">
												Contacter ${ enchere.articleVendu.utilisateur.pseudo }</a>
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
