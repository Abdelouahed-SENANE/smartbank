// Declaration des globals VAriable;
import {JOB_CONFIG} from "./config.js";
import {calcMonthly, calcMonths} from "./caculator.js";

const selectedJob = document.getElementById("profession")
const amountText = document.getElementById("amountText")
const amountInput = document.getElementById("amountInput")
const monthsInput = document.getElementById("months_range")
const monthsText = document.getElementById("monthsText")
const monthlyInput = document.getElementById("monthlyRange")
const monthlyText = document.getElementById("monthlyNumber")

amountText.value = amountInput.value;
monthsText.value = monthsInput.value;
monthlyText.value = monthlyInput.value;

document.addEventListener("DOMContentLoaded" , () => {
    const maxValues = JOB_CONFIG[selectedJob.value];
    if (maxValues) {
        amountInput.setAttribute("max" , maxValues.amountMax);
        monthsInput.setAttribute("max" , maxValues.monthsMax);
        monthsInput.setAttribute("min" , maxValues.monthsMin);
    }

    updateMonthly()
})

selectedJob.addEventListener("change", () => {
    const maxValues = JOB_CONFIG[selectedJob.value];
    if (maxValues) {
        amountInput.setAttribute("max" , maxValues.amountMax);
        monthsInput.setAttribute("max" , maxValues.monthsMax);
        monthsInput.setAttribute("min" , maxValues.monthsMin);
    }
    amountText.value = amountInput.value;
    monthsText.value = monthsInput.value;

})

amountInput.addEventListener("input" , () => {
    amountText.value = amountInput.value;
    updateMonthly()
})

monthsInput.addEventListener("input" , () => {
    monthsText.value = monthsInput.value;
    updateMonthly()
    monthlyText.value = monthlyInput.value
})

monthlyInput.addEventListener("input" , () => {
    monthlyText.value = monthlyInput.value;
    let currentMonth = calcMonths(amountInput.value , monthlyInput.value)
    monthsInput.value = monthsText.value = Math.round(currentMonth.toFixed(2));

})

function updateMonthly() {
    let minMonth = monthsInput.getAttribute("min");
    let maxMonth = monthsInput.getAttribute("max");

    let monthlyMinValue = calcMonthly(amountInput.value, maxMonth);
    let monthlyMaxValue = calcMonthly(amountInput.value, minMonth);
    let currentValue = calcMonthly(amountInput.value, monthsInput.value);
    monthlyInput.setAttribute("min" , monthlyMinValue.toFixed(2));
    monthlyInput.setAttribute("max" , monthlyMaxValue.toFixed(2));
    monthlyInput.setAttribute("value" ,currentValue.toFixed(2));
    monthlyText.value = monthlyInput.value
}



