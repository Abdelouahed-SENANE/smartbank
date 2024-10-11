// Declaration des globals VAriable;
import {JOB_CONFIG, PROJECT_CONFIG} from "./config.js";
import {calcMonthly, calcMonths} from "./caculator.js";
import {doSave} from "./requests.js";
import {showToast} from "./globals.js";

const selectedProject = document.getElementById("project")
const selectedJob = document.getElementById("profession")

const amountText = document.getElementById("amountText")
const amountInput = document.getElementById("amountInput")
const monthsInput = document.getElementById("months_range")
const monthsText = document.getElementById("monthsText")
const monthlyInput = document.getElementById("monthlyRange")
const monthlyText = document.getElementById("monthlyNumber")
const fees = document.getElementById("fees")
const email = document.getElementById("email")
const phone = document.getElementById("phone")



amountText.value = amountInput.value;
monthsText.value = monthsInput.value;
monthlyText.value = monthlyInput.value;

document.addEventListener("DOMContentLoaded", () => {
    const maxValues = JOB_CONFIG[selectedJob.value];
    if (maxValues) {
        amountInput.setAttribute("max", maxValues.amountMax);
        monthsInput.setAttribute("max", maxValues.monthsMax);
        monthsInput.setAttribute("min", maxValues.monthsMin);
    }
    updateMonthly()
    calcFees();
})

selectedJob.addEventListener("change", () => {
    const maxValues = JOB_CONFIG[selectedJob.value];
    if (maxValues) {
        amountInput.setAttribute("max", maxValues.amountMax);
        monthsInput.setAttribute("max", maxValues.monthsMax);
        monthsInput.setAttribute("min", maxValues.monthsMin);
    }
    amountText.value = amountInput.value;
    monthsText.value = monthsInput.value;

})

amountInput.addEventListener("input", () => {
    amountText.value = amountInput.value;
    updateMonthly();
    calcFees();
})

function calcFees() {
    const feesVal = (amountInput.value * 0.022).toFixed(2);
    if (feesVal <= 165) {
        fees.setAttribute("value", 165)
        fees.value = 165
    } else {
        fees.setAttribute("value", feesVal)
        fees.value = feesVal;
    }
}

monthsInput.addEventListener("input", () => {
    monthsText.value = monthsInput.value;
    updateMonthly();

})

monthlyInput.addEventListener("input", () => {

    monthlyText.value = monthlyInput.value;
    let currentMonth = calcMonths(amountInput.value, monthlyInput.value)
    monthsInput.value = monthsText.value = Math.round(currentMonth.toFixed(2));
    updateMonthly();

})

function updateMonthly() {
    let minMonth = monthsInput.getAttribute("min");
    let maxMonth = monthsInput.getAttribute("max");
    let currentValue = calcMonthly(amountInput.value, monthsInput.value).toFixed(2);

    let monthlyMinValue = calcMonthly(amountInput.value, maxMonth);
    let monthlyMaxValue = calcMonthly(amountInput.value, minMonth);
    monthlyInput.setAttribute("value", currentValue)
    monthlyText.setAttribute("value", currentValue)
    monthlyInput.setAttribute("min", monthlyMinValue.toFixed(2));
    monthlyInput.setAttribute("max", monthlyMaxValue.toFixed(2));
    monthlyText.value = monthlyInput.value = currentValue;

}

const phases = document.querySelectorAll(".phase")
const steps = document.querySelectorAll(".step")
const button = document.getElementById("nextBtn")
const submit = document.getElementById("submitBtn")

let currentPhase = 0;

function next() {
    if (currentPhase >= phases.length - 1) {
        return false
    }
    if (!validateForm()) {
        return false
    }
    currentPhase++;
    showPhase(currentPhase);
    displayInfo(currentPhase)

    if (currentPhase === phases.length - 1) {
        button.classList.replace("visible", "hidden");
        submit.classList.replace("hidden", "visible")
    }

}

button.addEventListener("click", (e) => {
    if (validateForm()) {
        next();
    }
});
submit.addEventListener("click", async (e) => {
    e.preventDefault();
    if (!validateForm()) {
        return
    }

    const form = document.getElementById("form_data")
    const payload = new FormData(form)

    if (payload) {
        try {
            const data = await  doSave(payload)
            console.log(data)
            showToast(data.status , data.message , "/creer");
        }catch (e){
            console.error(e)
        }
    }

});

steps.forEach((step, index) => {
    step.addEventListener("click", () => {
        if (index <= currentPhase) {
            currentPhase = index;
            submit.classList.replace("visible", "hidden");
            button.classList.replace("hidden", "visible")
            displayInfo(currentPhase)
            showPhase(index)
        }
    })
})

function showPhase(index) {
    phases.forEach(phase => phase.style.display = "none");
    steps.forEach(step => step.classList.remove("active", "passed"));

    phases[index].style.display = "block";

    for (let i = 0; i < index; i++) {
        steps[i].classList.add("passed");
    }
    steps[index].classList.add("active");
}

showPhase(currentPhase);
const popup = document.getElementById("popup")

function validateForm() {

    var inputs, valid = true;
    const errors = [];
    inputs = phases[currentPhase].getElementsByTagName("input");

    const fieldMapping = {
        phone: "Téléphone mobile est obligatoire",
        email: "Adresse email  est obligatoire.",
        last_name: "Nom est obligatoire",
        first_name: "Prénom est obligatoire",
        cin: "Numéro CIN / Carte de séjour est obligatoire",
        birth_date: "Date de naissance est obligatoire",
        hiring_date: "Date d'embauche/début de l'activité est obligatoire",
        income: "Total revenus mensuels (net en DH) est obligatoire",
    }
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            inputs[i].classList.add("invalid")
            const message = fieldMapping[inputs[i].name] || inputs[i].name
            errors.push(message)
            valid = false;

        } else {
            inputs[i].classList.remove("invalid")
        }

        if (inputs[i].value !== "") {
            if (inputs[i].name === "email") {
                const EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
                if (!inputs[i].value.match(EMAIL_REGEX)) {
                    inputs[i].classList.add("invalid");
                    errors.push("Veuillez saisir une adresse email valide.")
                    valid = false;
                } else {
                    inputs[i].classList.remove("invalid");
                }
            }

            if (inputs[i].name === "phone") {
                const PHONE_REGEX = /^0\d{9}$/;
                if (!inputs[i].value.match(PHONE_REGEX)) {
                    inputs[i].classList.add("invalid");
                    errors.push("Veuillez saisir un numéro de téléphone mobile valide")

                    valid = false;
                } else {
                    inputs[i].classList.remove("invalid");
                }
            }
            if (inputs[i].name === "last_name" || inputs[i].name === "first_name") {
                const REGEX_NAME = /^[a-zA-ZÀ-ÿ\s'-]+$/; //
                if (!inputs[i].value.match(REGEX_NAME)) {
                    inputs[i].classList.add("invalid");
                    const message = inputs[i].name === "last_name" ? "Le champ Nom ne doit contenir que des lettres" : "Le champ Prénom ne doit contenir que des lettres"
                    errors.push(message)
                    valid = false;
                } else {
                    inputs[i].classList.remove("invalid");
                }
            }
            if (inputs[i].name === "cin") {
                const CIN_REGEX = /^([A-Z]{1,2}\d{1,}|[a-z]{1,2}\d{1,})$/;
                if (!inputs[i].value.match(CIN_REGEX)) {
                    inputs[i].classList.add("invalid");
                    errors.push("CIN invalide. Veuillez entrer un CIN valide.")
                    valid = false;
                } else {
                    inputs[i].classList.remove("invalid");
                }
            }
            if (inputs[i].name === "birth_date" || inputs[i].name === "hiring_date") {
                const DATE_REGEX = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;
                if (!inputs[i].value.match(DATE_REGEX)) {
                    inputs[i].classList.add("invalid");
                    const message = inputs[i].name === "birth_date" ? "Veuillez saisir une date de naissance valide" : "Veuillez saisir une date d'embauche valide"
                    errors.push(message)
                    valid = false;
                } else {
                    inputs[i].classList.remove("invalid");
                }
            }
        }


    }
    const displayErrors = document.getElementById("errors")

    if (!valid) {

        popup.classList.replace("hidden", "visible")
        displayErrors.innerHTML = "";
        errors.forEach(error => {
            displayErrors.innerHTML += `
            <li>${error}</li>
            `
        })
    }
    return valid;
}

const closePopup = document.getElementById("close")
closePopup.addEventListener("click", () => popup.classList.replace("visible", "hidden"))

// ---- Format input Date  ---------
document.querySelectorAll(".date").forEach(inputDate => {
    inputDate.addEventListener("input", (e) => {
        let value = e.target.value;
        value = value.replace(/[^0-9]/g, '');
        if (value.length > 2 && value[2] !== "/") {
            value = value.slice(0, 2) + "/" + value.slice(2)
        }
        if (value.length > 5 && value[5] !== "/") {
            value = value.slice(0, 5) + "/" + value.slice(5)
        }

        e.target.value = value.slice(0, 10);
    })
})

selectedProject.addEventListener("change", () => {
    const project = document.getElementById("current_project")
    project.innerText = PROJECT_CONFIG[selectedProject.value];

})


function displayInfo(index) {
    const table = document.getElementById("table_info")
    table.innerHTML = "";
    switch (index) {
        case 0 :
            table.innerHTML += `
            
                <thead>
                <tr>
                    <td colspan="2">Mon projet</td>
                </tr>
                </thead>
                <tbody>
                <td class="text-primary">
                    <strong id="current_project">${PROJECT_CONFIG[selectedProject.value]}</strong>
                </td>
                </tbody>
            `
            break
        case 1 :
             table.innerHTML += `
                <thead>
                <tr>
                    <td colspan="2">Mon projet</td>
                </tr>
                </thead>
                <tbody>
                <td class="text-primary">
                    <strong id="current_project">${PROJECT_CONFIG[selectedProject.value]}</strong>
                </td>
                </tbody>
                        <thead>
                        <tr>
                            <td colspan="2">Détails de mon crédit</td>
                        </tr>
                        </thead>
                        <tbody id="body_info">
                        <tr>
                            <td class="text_td">Vous êtes:</td>
                            <td class="text_right text-primary result">
                                <span class="result">${selectedJob.selectedOptions[0].textContent}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Montant:</td>
                            <td class="text_right text-primary result">
                                <span class="result">${amountText.value}</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Durée:</td>
                            <td class="text_right text-primary result">
                                <span  class="result">${monthsText.value}</span>
                                <strong>mois</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Mensualité:</td>
                            <td class="text_right text-primary ">
                                <span class="result">${monthlyText.value}</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Frais de dossier:</td>
                            <td class="text_right text-primary result"><span>${fees.value}</span> <strong>DH</strong></td>
                        </tr>
                        </tbody>
             `
            break
        case 2 :
            table.innerHTML += `
                        <thead>
                        <tr>
                            <td colspan="2">Mon projet</td>
                        </tr>
                        </thead>
                        <tbody>
                        <td class="text-primary">
                            <strong id="current_project">${PROJECT_CONFIG[selectedProject.value]}</strong>
                        </td>
                        </tbody>
                        <thead>
                
                        <tr>
                            <td colspan="2">Coordonnées et infos personnelles</td>
                        </tr>
                        </thead>
                        <tbody id="body_info">
                        <tr>
                            <td class="text_td">Adresse email: </td>
                            <td class="text_right text-primary result">
                                <span class="result">${email.value}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Téléphone:</td>
                            <td class="text_right text-primary result">
                                <span class="result">${phone.value}</span>
                            </td>
                        </tr>

                        </tbody>
                        
                        
                        <thead>
                        <tr>
                            <td colspan="2">Détails de mon crédit</td>
                        </tr>
                        </thead>
                        <tbody id="body_info">
                        <tr>
                            <td class="text_td">Vous êtes:</td>
                            <td class="text_right text-primary result">
                                <span class="result">${selectedJob.selectedOptions[0].textContent}</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Montant:</td>
                            <td class="text_right text-primary result">
                                <span class="result">${amountText.value}</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Durée:</td>
                            <td class="text_right text-primary result">
                                <span  class="result">${monthsText.value}</span>
                                <strong>mois</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Mensualité:</td>
                            <td class="text_right text-primary ">
                                <span class="result">${monthlyText.value}</span> <strong>DH</strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="text_td">Frais de dossier:</td>
                            <td class="text_right text-primary result"><span>${(fees.value)}</span> <strong>DH</strong></td>
                        </tr>
                        </tbody>
             `
            break
    }


}
displayInfo(currentPhase)
