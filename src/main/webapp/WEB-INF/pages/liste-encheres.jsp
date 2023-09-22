<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf"%>

<main>
	<div class="row mt-5">
		<div class="col-8 offset-2">
			<table class="table table-dark">
				<thead>
					<tr>
						<th>Date</th>
						<th>Montant</th>
						<th>Nom de l'Article</th>
						<th>Utilisateur</th>
						<th>Actions</th>
					</tr>
				</thead>
<%-- 				<tbody>
					<c:forEach var="enchere" items="${ encheres }">
						<tr>
							<td>${ enchere.utilisateur.noUtilisateur }</td>
							<td>${ enchere.articleVendu.noArticle }</td>
							<td>${ enchere.dateEnchere }</td>
							<td>${ enchere.montantEnchere }</td>
							<td><a class="btn btn-dark"
								href="${ pageContext.request.contextPath }/details?id=${ enchere.articleVendu.noArticle }">
									<i class="fa-solid fa-eye"></i>
							</a></td>
						</tr>
					</c:forEach>
				</tbody> --%>
			</table>
		</div>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>
