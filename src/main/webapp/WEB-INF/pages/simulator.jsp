<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Credit Bank</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/globals.css">
    <script defer src="${pageContext.request.contextPath}/static/js/scripts.js"></script>
</head>
<body>
<div class="app">
    <div>
        <h1 class="main-title">Demander mon crédit en ligne</h1>
    </div>
    <div class="wrapper">
        <div class="row">
            <form action="" id="form_data">
                <div class="simulator">
                    <div class="steps__wrapper">
                        <div class="step active" onclick="goBackStep()">
                            <span class="number_step">1</span>
                            Simuler mon crédit
                        </div>
                        <div class="step" onclick="goBackStep()">
                            <span class="number_step">2</span>
                            Mes coordonnées
                        </div>
                        <div class="step" onclick="goBackStep()">
                            <span class="number_step">3</span>
                            Mes infos personnelles
                        </div>
                    </div>

                    <div class="simulator_wrapper">
                        <div id="step__1" style="display: block">
                            <div class="select_group">
                                <label>Mon projet</label>
                                <select name="project" id="">
                                    <option value="PRET_PERSONNEL">
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
                                <select id="profession" name="job" id="">
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
                                <span></span>
                            </div>

                            <div class="simulator_amount">
                                <label for="">Montant (en DH)</label>
                                <div class="simulator_range">
                                    <input
                                            id="amount_number"
                                            readonly
                                            class="field_number amount"
                                            type="number"
                                            step="any"
                                            name="amount"
                                            value="500000"
                                            aria-invalid="false"
                                    />
                                    <div>
                                        <input
                                                id="amount_range"
                                                class="range_field"
                                                type="range"
                                                min="5000"
                                                max="600000"
                                                step="1000"
                                        />
                                    </div>
                                </div>
                            </div>

                            <div class="simulator_amount">
                                <label>Durée (en mois)</label>
                                <div class="simulator_range">
                                    <input
                                            id="months"
                                            class="field_number duration"
                                            readonly
                                            type="number"
                                            step="any"
                                            value="24"
                                            name="duration"
                                            aria-invalid="false"
                                    />
                                    <div>
                                        <input
                                                class="range_field"
                                                type="range"
                                                min="12"
                                                max="120"
                                                step="1"
                                                value="24"
                                                id="months_range"
                                        />
                                    </div>
                                </div>
                            </div>

                            <div class="simulator_amount">
                                <label for="">Mensualités (en DH)</label>
                                <div class="simulator_range">
                                    <input
                                            class="field_number monthly"
                                            readonly
                                            id="monthlyNumber"
                                            type="number"
                                            step="1"
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
                            <!-- Button -->
                            <div class="submit__button" id="goToStep_2">
                    <span class="btn btn_primary">
                      Continuer
                      <small>Sans engagement</small>
                      <span class="form_old_submit">
                        <input
                                type="submit"
                                class="btn"
                                placeholder=" "
                                value="Continuer (sans engagement)"
                                autocomplete="off"
                        />
                      </span>
                    </span>
                            </div>
                            <input id="fees" type="hidden" name="fees" />
                        </div>
                        <!-- ========= Step 2 ========= -->
                        <div id="step__2" style="display: none">
                            <input
                                    type="text"
                                    name="email"
                                    required
                                    id=""
                                    placeholder="email"
                            />
                            <input
                                    type="text"
                                    name="phone"
                                    id=""
                                    required
                                    placeholder="phone"
                            />
                            <div class="submit__button" id="goToStep_3">
                    <span class="btn btn_primary">
                      Continuer
                      <small>Sans engagement</small>
                      <span class="form_old_submit">
                        <input
                                type="submit"
                                class="btn"
                                placeholder=" "
                                value="Continuer (sans engagement)"
                                autocomplete="off"
                        />
                      </span>
                    </span>
                            </div>
                        </div>

                        <!-- ========= Step 3========= -->
                        <div id="step__3" style="display: none">
                            <input
                                    type="text"
                                    name="email"
                                    required
                                    id=""
                                    placeholder="email"
                            />
                            <input
                                    type="text"
                                    name="phone"
                                    id=""
                                    required
                                    placeholder="phone"
                            />
                            <div class="submit__button" id="goToStep_3">
                    <span class="btn btn_primary">
                      Continuer
                      <small>Sans engagement</small>
                      <span class="form_old_submit">
                        <input
                                type="submit"
                                class="btn"
                                placeholder=" "
                                value="Continuer (sans engagement)"
                                autocomplete="off"
                        />
                      </span>
                    </span>
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
                    <table class="recap_table">
                        <thead>
                        <tr>
                            <td colspan="2">Mon projet</td>
                        </tr>
                        </thead>
                        <tbody>
                        <td class="text-primary">
                            <strong> Prêt Personnel </strong>
                        </td>
                        </tbody>

                        <thead style="display: none">
                        <tr>
                            <td colspan="2">Détails de mon crédit</td>
                        </tr>
                        </thead>
                        <tbody style="display: none">
                        <tr>
                            <td class="text_td">Vous êtes:</td>
                            <td class="text_right">Fonctionnaire</td>
                        </tr>
                        <tr>
                            <td class="text_td">Montant:</td>
                            <td class="text_right">
                                <span>10 000</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Durée:</td>
                            <td class="text_right">
                                <span>24</span>

                                <strong>mois</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Mensualité:</td>
                            <td class="text_right">
                                <span>469,40</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Frais de dossier:</td>
                            <td class="text_right">271,50 <strong>DH</strong></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>