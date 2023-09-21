<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

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
		<tbody>
		<tbody>
			<c:forEach var="encheres" items="${ listEncheres }" >
				<tr>
					<td>${ encheres.utilisateur.noUtilisateur }</td>
					<td>${ encheres.articleVendu.noArticle }</td>
					<td>${ encheres.dateEnchere }</td>
					<td>${ encheres.montantEnchere }</td>
					<td>
						<a class="btn btn-dark" 
							href="${ pageContext.request.contextPath }/details?id=${ encheres.articleVendu.noArticle }">
							<i class="fa-solid fa-eye"></i>
						</a> 
					</td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
		</div>	
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
