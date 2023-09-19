<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/fragments/header.jspf" %>
<main class="row">
	<div class="col">	
		<div class="row mt-5">
			<div class="col-8 offset-2">
				<c:if test="${ !empty success }">
					<div class="alert alert-success">${ success }</div>
				</c:if>
				<c:if test="${ !empty error }">
					<div class="alert alert-danger">${ error }</div>
				</c:if>
				<c:if test="${ param.err == 1 }">
					<div class="alert alert-danger">Pour supprimer un jeu faut se
						connecter</div>
				</c:if>
				<form method="post">
					<div class="mb-3">
						<label for="username" class="form-label">Identifiant: </label> <input
							type="text" class="form-control" name="username" id="username"
							placeholder="ex. toto">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Mot de passe: </label> <input
							type="password" class="form-control" name="password"
							id="password">
					</div>
					<button class="btn btn-primary" role="button" type="submit">Connexion</button>
					<div class="mb-3">
						<a href="${ pageContext.request.contextPath }/forget-password">Mot
							de passe oublié</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</main> --%>
<%@ include file="/WEB-INF/fragments/footer.jspf"%>