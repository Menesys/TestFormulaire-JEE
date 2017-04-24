<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Formulaire</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      			rel="stylesheet">
    </head>
    <body>
    
    <form class="form-horizontal" method="post" action="FormServlet">
	<fieldset>
	
	<legend>Formulaire d'inscription</legend>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nom">Nom</label>  
	  <div class="col-md-4">
	  <input id="nom" name="nom" placeholder="Nom" class="form-control input-md" type="text" value="${param.nom}">
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="prenom">Prénom</label>  
	  <div class="col-md-4">
	  	<input id="prenom" name="prenom" placeholder="Prénom" class="form-control input-md" type="text" value="${param.prenom}">    
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="naissance">Date de naissance</label>  
	  <div class="col-md-4">
	  <input id="naissance" name="naissance" placeholder="Date de naissance" class="form-control input-md" type="text" value="${param.naissance}">
	  <span class="help-block">Format jj/mm/aaaa</span>  
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="email">Adresse e-mail *</label>  
	  <div class="col-md-4">
	  <input id="email" name="email" placeholder="Email" class="form-control input-md" required="required" type="text" value="${param.email}">	    
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="mdp">Mot de passe *</label>
	  <div class="col-md-4">
	    <input id="mdp" name="mdp" placeholder="Mot de passe" class="form-control input-md" required="required" type="password">  
	  	<span class="help-block">Le mot de passe doit comporter au moins 5 caractères</span>  
	  </div>
	</div>
	
	<div class="form-group">
	  <label class="col-md-4 control-label" for="confirmation">Confirmer le mot de passe *</label>
	  <div class="col-md-4">
	    <input id="confirmationmdp" name="confirmationmdp" placeholder="Confirmation" class="form-control input-md" required="required" type="password">
	    
	  </div>
	</div>
	
	<div class="form-group">
	  <div class="col-md-4 col-md-offset-4">
		<input type="submit" class="btn btn-info" value="Inscription" />	        
	  </div>
	</div>
	
	<div class="form-group">
	  <div class="col-md-4 col-md-offset-4">
		<p>
	    <c:if test="${!result}">
			<p class="bg-danger">
				<c:out value="${erreurs}"/>
			</p>
		</c:if>
					
		<c:if test="${result}">
			<p class="bg-success">
				<c:out value="Succès !"/>
			</p>
		</c:if>	        
	  </div>
	</div>	
	
	</fieldset>
	</form>
	    
    </body>
</html>