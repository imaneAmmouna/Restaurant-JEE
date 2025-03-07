<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<style>
/* Général */
body {
    margin: 0;
    padding: 0;
    background:#fffcec;
}

h1 {
    color: #333;
    text-align: center;
    margin-top: 20px;
}

/* Conteneur principal */
.profile-container {
    max-width: 600px;
    margin: 80px auto;
    background: #e3ceb5;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

/* Informations du profil */
.profile-info p {
    margin: 10px 0;
    font-size: 16px;
    color: #555;
}

.profile-info strong {
    color: #222;
}

/* Actions du profil */
.profile-actions {
    text-align: center;
    margin-top: 20px;
}

.profile-actions .btn {
    display: inline-block;
    padding: 10px 15px;
    margin: 5px;
    font-size: 14px;
    text-decoration: none;
    border-radius: 5px;
    color: #fff;
    transition: background-color 0.3s ease;
}

.profile-actions .edit-btn {
    background-color: #6a3d2a;
    color:#fff;
}

.profile-actions .edit-btn:hover {
    background-color: #feac53;
    color:#6a3d2a;
}


</style>
</head>
<body>
	<%@ include file="/includes/header_client.jsp" %>
<div class="profile-container">
        <h1>Profil de ${sessionScope.prenom} ${sessionScope.nom}</h1>
        <div class="profile-info">
            <p><strong>Nom :</strong> ${sessionScope.nom}</p>
            <p><strong>Prénom :</strong> ${sessionScope.prenom}</p>
            <p><strong>Email :</strong> ${sessionScope.email}</p>
            <p><strong>Téléphone :</strong>${sessionScope.telephone}</p>
            <p><strong>Date de création :</strong> ${sessionScope.created_at}</p>
        </div>
        <div class="profile-actions">
            <a href="edit_profile.jsp" class="btn edit-btn"><b>Modifier le profil</b></a>
        </div>
    </div>
</body>
</html>