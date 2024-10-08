
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="update_form_container">

    <div class="popup_wrapper">
        <div class="overlay_popup"></div>
        <div class="form_wrapper">
            <form id="update_form" >
                <div class="update_demandes">
                    <div class="parts__wrapper">
                        <div class="part passed">
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
                            <div class="phase" style="display: block">
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
                                <input id="fees" type="hidden" value="" name="fees"/>
                            </div>

                            <%-- ==== phase 2 ======= --%>
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
                                <div class="navigation_btns">
                                    <button class="btn btn_primary" type="button" id="prevBtn">
                                        Prev
                                    </button>
                                    <button class="btn btn_primary " type="button" id="nextBtn">
                                        Next
                                    </button>
                                    <button class="btn btn_primary " type="submit" id="submitBtn">
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
