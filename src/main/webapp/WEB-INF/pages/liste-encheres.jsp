<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>

<main>
	<h1>LOSNA</h1>
	<div>
		<c:forEach var="game" items="${ games }" >
			<tr>
				<td>${ game.id }</td>
				<td>${ game.name }</td>
				<td>${ game.company }</td>
				<td>${ game.category }</td>
				<td>${ game.price }</td>
				<td>
					<a class="btn btn-dark" 
						href="${ pageContext.request.contextPath }/jeux/détails?id=${ game.id }">
						<i class="fa-solid fa-eye"></i>
					</a> 
				</td>
			</tr>
		</c:forEach>
	</div>
</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
