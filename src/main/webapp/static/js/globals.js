// Declaration des globals VAriable;
import {JOB_CONFIG} from "./config.js";
import {calcMonthly, calcMonths} from "./caculator.js";

const selectedProject = document.getElementById("project")
const selectedJob = document.getElementById("profession")

const amountText = document.getElementById("amountText")
const amountInput = document.getElementById("amountInput")
const monthsInput = document.getElementById("months_range")
const monthsText = document.getElementById("monthsText")
const monthlyInput = document.getElementById("monthlyRange")
const monthlyText = document.getElementById("monthlyNumber")
const selectedOption = document.getElementsByClassName("selected-option")
const fees = document.getElementById("fees")
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
    selectedOption[0].innerText = selectedProject.options[selectedProject.selectedIndex].innerText;
    selectedOption[1].innerText = selectedJob.options[selectedJob.selectedIndex].innerText;
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
    selectedOption[1].innerText = selectedJob.options[selectedJob.selectedIndex].innerText;

})
selectedProject.addEventListener("change", () => {
    selectedOption[0].innerText = selectedProject.options[selectedProject.selectedIndex].innerText;

})

amountInput.addEventListener("input", () => {
    amountText.value = amountInput.value;
    updateMonthly();
    // Cacul Fees
    calcFees();
})

function calcFees() {
    const feesVal = amountInput.value * 0.022;
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

let currentPhase = 2;

function next() {
    if (currentPhase >= phases.length - 1) {
        return false
    }
    if (!validateForm()) {
        return false
    }
    currentPhase++;
    showPhase(currentPhase);
    if (currentPhase === phases.length - 1) {
        button.setAttribute("type", "submit");
        button.innerText = "Demande ce credit"
    }
}

button.addEventListener("click", (e) => {
    if (currentPhase < phases.length - 1 || !validateForm()) {
        e.preventDefault();
    }
    next();
});
steps.forEach((step, index) => {
    step.addEventListener("click", () => {
        if (index <= currentPhase) {
            currentPhase = index;
            button.setAttribute("type", "button");
            button.innerHTML = "Continuer\n" +
                "<small>(Sans engagement)</small>"
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
        phone : "Téléphone mobile est obligatoire",
        email : "Adresse email  est obligatoire.",
        last_name : "Nom est obligatoire",
        first_name : "Prénom est obligatoire",
        cin : "Numéro CIN / Carte de séjour est obligatoire",
        birth_date : "Date de naissance est obligatoire",
        hiring_date : "Date d'embauche/début de l'activité est obligatoire",
        income : "Total revenus mensuels (net en DH) est obligatoire",
    }
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            inputs[i].classList.add("invalid")
            const message = fieldMapping[inputs[i].name ] || inputs[i].name
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
                const CIN_REGEX = /^[A-Z]{1}\d{14}$/;
                if (!inputs[i].name.match(CIN_REGEX)) {
                    errors.push("CIN invalide. Veuillez entrer un CIN valide.")
                    valid = false;
                }
            }
            if (inputs[i].name === "birth_date" || inputs[i].name === "hiring_date") {
                const DATE_REGEX = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[0-2])\/\d{4}$/;
                if (!inputs[i].name.match(DATE_REGEX)) {
                    const message = inputs[i].name === "birth_date" ? "Veuillez saisir une date de naissance valide" : "Veuillez saisir une date d'embauche valide"
                    errors.push(message)
                    valid = false;
                }
            }
        }


    }
    const displayErrors =document.getElementById("errors")

    if(!valid) {

        popup.classList.replace("hidden" , "visible")
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
closePopup.addEventListener("click" , () =>popup.classList.replace("visible" , "hidden"))