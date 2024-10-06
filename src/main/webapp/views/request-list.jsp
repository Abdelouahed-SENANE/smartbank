<%--
  Created by IntelliJ IDEA.
  User: youcode
  Date: 10/4/24
  Time: 12:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Voir tous les demande </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/globals.css">
<%--    <script type="module" defer src="${pageContext.request.contextPath}/static/js/simulator.js"></script>--%>
    <link rel="icon" href="../static/img/favicon-16x16.png"/>
</head>
<body>
<div class="table_container">
    <div class="table_wrapper">
        <div class="_header">
            <h2>Tableau des Demandes</h2>
            <div class="wrapper_inputs">
               <div>
                   <input type="text" name="query" id="search-input" placeholder="Search...">
               </div>
                <div>
                    <input type="date" name="date" id="input-date">
                </div>
            </div>
        </div>
        <div class="table_item">
            <table>
                <thead>
                <tr>
                    <th class="column_1">ID</th>
                    <th class="column_2">Prenom</th>
                    <th class="column_3">Nom</th>
                    <th class="column_4">Cin</th>
                    <th class="column_5">Montant (DH)</th>
                    <th class="column_6">Address Email</th>
                    <th class="column_7">Mois</th>
                    <th class="column_8">Salaire (DH)</th>
                    <th class="column_9">Mensualité</th>
                    <th class="column_10">Frais de dossier</th>
                    <th class="column_11">Date de naissance</th>
                    <th class="column_12">Fonction</th>
                    <th class="column_13">Phone</th>
                    <th class="column_14">Nom de projet</th>
                    <th class="column_15">Civilite</th>
                    <th class="column_16">Historique status</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${requests}">

                    <tr>
                        <td class="column_1">${request.requestId}</td>
                        <td class="column_2">${request.firstname}</td>
                        <td class="column_3">${request.lastname}</td>
                        <td class="column_4">${request.cin}</td>
                        <td class="column_5">${request.amount}</td>
                        <td class="column_6">${request.email}</td>
                        <td class="column_7">${request.duration}</td>
                        <td class="column_8">${request.income}</td>
                        <td class="column_9">${request.monthly}</td>
                        <td class="column_10">${request.fees}</td>
                        <td class="column_11">${request.birthday}</td>
                        <td class="column_12">${request.jobName}</td>
                        <td class="column_13">${request.phone}</td>
                        <td class="column_14">${request.projectName}</td>
                        <td class="column_15">${request.civility}</td>
                        <td class="column_16">Etat</td>

                    </tr>
                </c:forEach>




                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>
