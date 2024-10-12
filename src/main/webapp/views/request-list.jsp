
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Voir tous les demande </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/globals.css">
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/list-requests.js"></script>
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/simulator.js"></script>
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/update-form.js"></script>

    <link rel="icon" href="../static/img/favicon-16x16.png"/>
</head>
<body>
<div class="table_container">
    <div class="table_wrapper">
        <div class="_header">
            <h2>Tableau des Demandes</h2>
            <form action="/demandes/liste" method="GET">
                <div class="wrapper_inputs">
                    <label for="search-input"  class="custom_txt">
                        <input type="text" name="status" id="search-input" placeholder="Search by status...">
                    </label>
                    <label for="date-input" class="custom-date">
                        <input type="date" name="creationDate" id="date-input" placeholder="Enter date">
                    </label>
                    <div class="filter_btn_container">
                        <button class="search_btn" id="btn_search" type="submit">Search</button>
                        <button class="reset_btn" type="reset">
                            <a href="http://localhost:8080/demandes/liste">Reset</a>
                        </button>
                    </div>

                </div>
            </form>
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
                    <th class="column_2">Date de creation</th>
                    <th class="column_17">Date d'embauche</th>
                    <th class="column_18">Actions</th>

                </tr>
                </thead>
                <tbody>

                <c:choose>
                    <c:when test="${not empty requests}">
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
                                <td class="column_2">${request.createdAt}</td>
                                <td class="column_17">${request.dateOfHire}</td>

                                <td class="column_17">
                                    <div class="actions_wrapper">
                                        <button class="action_btn view" type="button">
                                            Voir historiques
                                        </button>
                                        <button data-id="${request.requestId}" data-status="${(request.statusHistories[0].status.statusName).toLowerCase()}" class="action_btn update_status" type="button">
                                            Edit status
                                        </button>
                                        <button class="action_btn update" type="button" data-id="${request.requestId}">Edit</button>
                                        <form class="delete_form" style="display: none">
                                            <input type="hidden" name="requestId" value="${request.requestId}">
                                        </form>
                                        <button class="action_btn delete" type="button" data-id="${request.requestId}">
                                            Supp
                                        </button>
                                    </div>
                                    <div class="histories_table histories" id="histories">
                                        <div class="overlay_table"></div>
                                        <div class="sub_table">
                                            <div class="sub_table_container">
                                                <div class="_header">
                                                    <h2>Tableau des Historiques de ${request.projectName}</h2>
                                                    <div class="wrapper_inputs">
                                                        <!-- Additional inputs can go here -->
                                                    </div>
                                                </div>
                                                <table>
                                                    <thead>
                                                    <tr>
                                                        <th class="column_1">Historique ID</th>
                                                        <th class="column_2">Etat</th>
                                                        <th class="column_3">Modifié le</th>
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
                    </c:when>
                    <c:otherwise>
                        <td class="message_data" colspan="10">
                            <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" fill="currentColor" class="bi bi-database-fill-x" viewBox="0 0 16 16">
                                <path d="M8 1c-1.573 0-3.022.289-4.096.777C2.875 2.245 2 2.993 2 4s.875 1.755 1.904 2.223C4.978 6.711 6.427 7 8 7s3.022-.289 4.096-.777C13.125 5.755 14 5.007 14 4s-.875-1.755-1.904-2.223C11.022 1.289 9.573 1 8 1"/>
                                <path d="M2 7v-.839c.457.432 1.004.751 1.49.972C4.722 7.693 6.318 8 8 8s3.278-.307 4.51-.867c.486-.22 1.033-.54 1.49-.972V7c0 .424-.155.802-.411 1.133a4.51 4.51 0 0 0-4.815 1.843A12 12 0 0 1 8 10c-1.573 0-3.022-.289-4.096-.777C2.875 8.755 2 8.007 2 7m6.257 3.998L8 11c-1.682 0-3.278-.307-4.51-.867-.486-.22-1.033-.54-1.49-.972V10c0 1.007.875 1.755 1.904 2.223C4.978 12.711 6.427 13 8 13h.027a4.55 4.55 0 0 1 .23-2.002m-.002 3L8 14c-1.682 0-3.278-.307-4.51-.867-.486-.22-1.033-.54-1.49-.972V13c0 1.007.875 1.755 1.904 2.223C4.978 15.711 6.427 16 8 16c.536 0 1.058-.034 1.555-.097a4.5 4.5 0 0 1-1.3-1.905"/>
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m-.646-4.854.646.647.646-.647a.5.5 0 0 1 .708.708l-.647.646.647.646a.5.5 0 0 1-.708.708l-.646-.647-.646.647a.5.5 0 0 1-.708-.708l.647-.646-.647-.646a.5.5 0 0 1 .708-.708"/>
                            </svg>
                            <br>
                            <span>
                                Les données n'existent pas
                            </span>
                        </td>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>

    <%-- Toasts --%>
    <div class="toast_wrapper hidden" id="toast_wrapper">
        <div class="toast_card" id="toast_card">
            <div class="toast_header">
                <h3 id="toast_title"></h3>
                <button>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8z"/>
                    </svg>
                </button>
            </div>
            <p id="toast_message"></p>
        </div>
    </div>
</div>

<div class="status_form_container">
    <div id="status_form" class="popup_status from hidden">
        <div id="overlay_status" class="overlay_popup"></div>
        <div class="form_wrapper">
            <form id="update_status_form">
                <div class="update_demandes">
                    <div class="simulator_wrapper">
                        <div >
                            <h4 >Status : <span id="status_actuel"> En attende</span></h4>
                            <div class="select_group">
                                <label for="project">Etat</label>
                                <select name="status" id="status">
                                    <option selected value="EN ATTENTE">En attente</option>
                                    <option value="APPROUVÉ">Approuvé</option>
                                    <option value="REJETÉ">Rejeté</option>
                                    <option value="TERMINÉ" >Terminé</option>
                                </select>
                            </div>
                            <input id="status_requestId" type="hidden" name="requestId"/>
                        </div>

                        <!-- Button -->
                        <div class="submit__button">
                            <button class="btn btn_primary" type="button"  id="submit_status">
                                Submit
                            </button>
                        </div>

                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

<div class="update_form_container">

    <div id="wrapper_form"  class="popup_wrapper from hidden">
        <div id="overlay_update" class="overlay_popup"></div>
        <div class="form_wrapper ">
            <form id="update_form" >
                <div class="update_demandes">
                    <div class="parts__wrapper">
                        <div class="part active">
                            <span class="number_part">1</span>
                            <span class="line"></span>
                        </div>
                        <div class="part">
                            <span class="number_part">2</span>
                        </div>
                    </div>

                    <div class="simulator_wrapper">
                        <div class="phases">
                            <%--  ==== phase 1 =====--%>
                            <div class="step" style="display: block">

                                <div class="select_group">
                                    <label for="project">Mon projet</label>
                                    <select name="project" id="project">
                                        <option selected value="PRET_PERSONNEL">
                                            J’ai besoin d’argent
                                        </option>
                                        <option value="OCCASION">
                                            Je finance mon véhicule d’occasion
                                        </option>
                                        <option value="REV">Je Gère mes imprévus</option>
                                        <option value="CREDIT_AUTOMOBILE">
                                            Je finance mon véhicule neuf
                                        </option>
                                        <option value="CREDIT_MENAGE">J’équipe ma maison</option>
                                    </select>
                                </div>
                                <div class="select_group">
                                    <label>Je suis</label>
                                    <select id="profession" name="job">
                                        <option value="SALARIE_PRIVE">
                                            Salarié du secteur privé
                                        </option>
                                        <option selected value="SALARIE_PUBLIC">
                                            Fonctionnaire
                                        </option>
                                        <option value="PROFESSION_LIBERALE">
                                            Profession libérale
                                        </option>
                                        <option value="COMMERCANT">Commerçant</option>
                                        <option value="ARTISAN">Artisan</option>
                                        <option value="RETRAITE">Retraité</option>
                                        <option value="AUTRE">Autres professions</option>
                                    </select>
                                </div>

                                <div class="simulator_amount">
                                    <label >Montant (en DH)</label>
                                    <div class="simulator_range">
                                        <input
                                                id="amountText"
                                                readonly
                                                class="field_number amount"
                                                type="number"
                                                step="1000"
                                                name="amount"
                                                aria-invalid="false"
                                        />
                                        <div>
                                            <input
                                                    id="amountInput"
                                                    class="range_field"
                                                    type="range"
                                                    min="5000"
                                                    step="1000"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div class="simulator_amount">
                                    <label>Durée (en mois)</label>
                                    <div class="simulator_range">
                                        <input
                                                id="monthsText"
                                                class="field_number duration"
                                                readonly
                                                type="number"
                                                step="6"
                                                value="12"
                                                name="duration"
                                                aria-invalid="false"
                                        />
                                        <div>
                                            <input
                                                    class="range_field"
                                                    type="range"
                                                    step="1"
                                                    id="months_range"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <div class="simulator_amount">
                                    <label>Mensualités (en DH)</label>
                                    <div class="simulator_range">
                                        <input
                                                class="field_number monthly"
                                                readonly
                                                id="monthlyNumber"
                                                type="number"
                                                step="any"
                                                name="monthly"
                                                value=""
                                                aria-invalid="false"
                                        />
                                        <div>
                                            <input
                                                    class="range_field"
                                                    type="range"
                                                    step="1"
                                                    id="monthlyRange"
                                            />
                                        </div>
                                    </div>
                                </div>
                                <div class="form_control">
                                    <label class="label_form invalid" for="email">Email*</label>
                                    <input class="field_form" name="email" id="email" type="text" placeholder="Email*">
                                </div>
                                <div class="form_control">
                                    <label class="label_form" for="email"> Téléphone mobile*</label>
                                    <input class="field_form " name="phone" id="phone" type="text" placeholder="Téléphone mobile*">
                                </div>
                                <input id="fees" type="hidden"  name="fees"/>
                                <input id="requestId" type="hidden" name="requestId"/>

                            </div>

                            <%-- ==== phase 2 ======= --%>
                            <div class="step" style="display: none">
                                <fieldset>
                                    <legend class="pannel_heading">
                                        <span>Civilité</span>
                                    </legend>
                                    <div class="fieldset_wrapper">
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" checked name="civility" placeholder=" " value="MADAME"/>
                                                <span class="custom_field"></span>
                                                <span>Madame</span>
                                            </label>
                                        </div>
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" name="civility" placeholder=" " value="MADEMOISELLE"/>
                                                <span class="custom_field"></span>
                                                <span>Mademoiselle</span>
                                            </label>

                                        </div>
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" name="civility" placeholder=" " value="MONSIEUR"/>
                                                <span class="custom_field"></span>
                                                <span>Monsieur</span>
                                            </label>

                                        </div>
                                    </div>
                                </fieldset>

                                <div class="form_control">
                                    <label class="label_form invalid" for="lastName">Nom*</label>
                                    <input class="field_form" name="last_name" id="lastName" type="text" placeholder="Nom*">
                                </div>

                                <div class="form_control">
                                    <label class="label_form invalid" for="firstName">Prénom*</label>
                                    <input class="field_form" name="first_name" id="firstName" type="text" placeholder=" Prénom*">
                                </div>

                                <div class="form_control">
                                    <label class="label_form invalid" for="cin"> Numéro CIN / Carte de séjour*</label>
                                    <input class="field_form" name="cin" id="cin" type="text" placeholder=" Numéro CIN / Carte de séjour*">
                                </div>

                                <div class="form_control">
                                    <label class="label_form" for="edit_birth_date"> Date de naissance*</label>
                                    <input class="field_form date" name="birth_date" id="edit_birth_date" type="text" placeholder="JJ/MM/YYYY">
                                </div>

                                <div class="form_control">
                                    <label class="label_form  " for="edit_hiring_date">Date d'embauche/début de l'activité*</label>
                                    <input class="field_form date" name="hiring_date" id="edit_hiring_date" type="text" placeholder="JJ/MM/YYYY">
                                </div>

                                <div class="form_control">
                                    <label class="label_form " for="edit_income">Total revenus mensuels (net en DH)*</label>
                                    <input class="field_form" name="income" id="edit_income" step="1" type="number" placeholder="Total revenus mensuels (net en DH)">
                                </div>

                                <fieldset>
                                    <legend class="pannel_heading">
                                        <span> Avez vous des crédits en cours ?</span>
                                    </legend>
                                    <div class="fieldset_wrapper">
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" name="has_credit" placeholder=" " value=""/>
                                                <span class="custom_field"></span>
                                                <span>Oui</span>
                                            </label>
                                        </div>
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" checked name="has_credit" placeholder=" " value=""/>
                                                <span class="custom_field"></span>
                                                <span>Non</span>
                                            </label>
                                        </div>
                                    </div>
                                </fieldset>

                                <div class="form_control_checkbox">
                                    <input type="checkbox" name="use_terms" value="1" autocomplete="off">
                                    <span>J'ai lu et j'accepte les conditions générales d'utilisation figurant sur les informations légales, notamment la mention relative à la protection des données personnelles</span>
                                </div>

                            </div>
                            <!-- Button -->
                            <div class="submit__button">
                                <div class="collections_btn">
                                    <button class="btn btn_primary inactive" type="button" id="prevBtn_update">
                                        Prev
                                    </button>
                                    <button class="btn btn_primary " type="button" id="nextBtn_update">
                                        Next
                                    </button>
                                    <button class="btn btn_primary " type="submit" id="submit_update" style="display: none">
                                        Submit
                                    </button>
                                </div>

                            </div>

                        </div>


                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

</body>
</html>
