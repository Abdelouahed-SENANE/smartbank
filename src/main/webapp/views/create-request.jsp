<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Demander mon crédit en ligne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/globals.css">
    <script type="module" defer src="${pageContext.request.contextPath}/static/js/simulator.js"></script>
    <link rel="icon" href="../static/img/favicon-16x16.png"/>
</head>
<body>
<div class="app">
    <div>
        <h1 class="main-title">Demander mon crédit en ligne</h1>
    </div>

    <div class="wrapper">
        <div class="back_link">
                <a href="http://localhost:8080/">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8"/>
                    </svg>
                    <span>Retour</span>
                </a>
        </div>
        <div class="row">

            <form id="form_data" >
                <div class="simulator">
                    <div class="steps__wrapper">
                        <div class="step active">
                            <span class="number_step">1</span>
                            Simuler mon crédit
                        </div>
                        <div class="step">
                            <span class="number_step">2</span>
                            Mes coordonnées
                        </div>
                        <div class="step">
                            <span class="number_step">3</span>
                            Mes infos personnelles
                        </div>
                    </div>

                    <div class="simulator_wrapper">
                        <div class="phases">

                            <%--  ==== phase 1 =====--%>
                            <div class="phase" style="display: block">
                                <div class="select_group">
                                    <label for="project">Mon projet</label>
                                    <select name="project" id="project">
                                        <option selected value="PRET_PERSONNEL">
                                            J'ai besoin d'argent
                                        </option>
                                        <option value="OCCASION">
                                            Je finance mon véhicule d’occasion
                                        </option>
                                        <option value="REV">Je Gère mes imprévus</option>
                                        <option value="CREDIT_AUTOMOBILE">
                                            Je finance mon véhicule neuf
                                        </option>
                                        <option value="CREDIT_MENAGE">J'équipe ma maison</option>
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

                                <input id="fees" type="hidden" value="" name="fees"/>
                            </div>
                            <%-- ====== Phase 2 ========--%>
                            <div class="phase" style="display: none">
                                <div class="form_control">
                                    <label class="label_form invalid" for="email">Email*</label>
                                    <input class="field_form" name="email" id="email" type="text" placeholder="Email*">
                                </div>
                                <div class="form_control">
                                    <label class="label_form" for="email"> Téléphone mobile*</label>
                                    <input class="field_form " name="phone" id="phone" type="text" placeholder="Téléphone mobile*">
                                </div>
                            </div>
                            <%-- ==== phase 3 ======= --%>
                            <div class="phase" style="display: none">
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
                                                <input type="radio" name="has_credit" placeholder=" " value="MADAME"/>
                                                <span class="custom_field"></span>
                                                <span>Oui</span>
                                            </label>
                                        </div>
                                        <div class="fieldset_control">
                                            <label>
                                                <input type="radio" checked name="has_credit" placeholder=" " value="MADAME"/>
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
                                <button class="btn btn_primary visible" type="button" id="nextBtn">
                                    Continuer
                                    <small>(Sans engagement)</small>
                                </button>
                                <button class="btn btn_primary hidden" type="submit" id="submitBtn">
                                    Demander ce crédit
                                </button>
                            </div>

                        </div>


                    </div>
                    <div class="box_shadow">
                        <div class="copyright_bloc">
                            <p>
                                Simulation à titre indicatif et non contractuelle. La
                                mensualité minimale est de 180 dirhams. Un client Wafasalaf
                                peut bénéficier d'une tarification plus avantageuse en
                                fonction de ses conditions préférentielles.
                            </p>
                            <p>
                                Conformément à la loi 09-08, vous disposez d’un droit
                                d’accès, de rectification et d’opposition au traitement de
                                vos données personnelles. Ce traitement est autorisé par la
                                CNDP sous le numéro A-GC-206/2014.
                            </p>
                        </div>
                    </div>
                </div>
            </form>

            <div class="recap">
                <div class="recap_wrapper">
                    <h2 class="recap-title">Mon récapitulatif</h2>
                    <table class="recap_table" id="table_info">



                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%--======== popup to show errors ======== --%>

<div class="popup_wrapper hidden" id="popup">
    <div class="overlay_popup"></div>
    <div class="content_popup">
        <div class="errors_container">
            <ul class="alert_messages" id="errors"></ul>

        </div>
        <div class="popup_footer">
            <div class="close_popup_container">
                <button class="close_popup btn_primary" type="button" id="close">
                    Fermer
                </button>
            </div>
        </div>
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
</body>
</html>