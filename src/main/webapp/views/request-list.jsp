
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Voir tous les demande </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/globals.css">
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/list-requests.js"></script>
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/simulator.js"></script>

    <link rel="icon" href="../static/img/favicon-16x16.png"/>
</head>
<body>
<div class="table_container">
    <div class="table_wrapper">
        <div class="_header">
            <h2>Tableau des Demandes</h2>
            <div class="wrapper_inputs">
               <label for="search-input"  class="custom_txt">
                   <input type="text" name="query" id="search-input" placeholder="Search by name...">
               </label>
                <label for="date-input" class="custom-date">
                    <input type="date" id="date-input" placeholder="Enter date">
                </label>
                <button class="search" id="btn_search" type="submit">Search</button>
            </div>
        </div>
        <div class="table_item">
            <table>
                <thead>
                <tr>
                    <th class="column_1">Demande ID</th>
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
                    <th class="column_16">Etat</th>
                    <th class="column_17">Actions</th>

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
                        <td class="column_16">${(request.statusHistories[0].status.statusName).toLowerCase()}</td>

                        <td class="column_17">
                            <div class="actions_wrapper">
                                <button  class="action_btn view" type="button"  >
                                    View
                                </button>
                                <button class="action_btn update"  type="button"  >Edit</button>
                                <button class="action_btn delete"  type="button"  data-id="${request.requestId}">
                                    Supp
                                </button>
                            </div>
                            <div class="histories_table histories" id="histories">
                                <div class="overlay_table" ></div>
                                <div class="sub_table">

                                    <div class="sub_table_container">
                                        <div class="_header">
                                            <h2>Tableau des Historiques de ${request.projectName}</h2>
                                            <div class="wrapper_inputs">
                                            </div>
                                        </div>
                                        <table>
                                            <thead>
                                            <tr>
                                                <th class="column_1">Historique ID</th>
                                                <th class="column_2">Etat</th>
                                                <th class="column_1">Modifié le</th>
                                                <th class="column_14">Nom de projet</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="history" items="${request.statusHistories}">
                                                <tr>
                                                    <td class="column_1">${history.historyId}</td>
                                                    <td class="column_2">${history.status.statusName}</td>
                                                    <td class="column_3">${history.changedAt}</td>
                                                    <td class="column_14">${history.request.projectName}</td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </td>

                    </tr>
                </c:forEach>




                </tbody>
            </table>
        </div>
    </div>
<%--    <jsp:include page="components/update-form.jsp"/>--%>

</div>


</body>
</html>
