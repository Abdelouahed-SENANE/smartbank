import {doUpdate} from "./requests.js";
import {showToast} from "./globals.js";


const updateButtons = document.querySelectorAll(".update")
let currentStep = 0;

updateButtons.forEach(el => {
    el.addEventListener("click" , (e) => {
        handleStep(-1)
        document.getElementById("wrapper_form").classList.remove("hidden");
        const tr = e.currentTarget.closest("tr");
        document.getElementById("requestId").value = tr.querySelector(".column_1").textContent;
        document.getElementById("email").value = tr.querySelector(".column_6").textContent;
        document.getElementById("phone").value = tr.querySelector(".column_13").textContent;
        document.getElementById("amountText").value = tr.querySelector(".column_5").textContent;
        document.getElementById("amountInput").value = tr.querySelector(".column_5").textContent;
        document.getElementById("monthsText").value = tr.querySelector(".column_7").textContent;
        document.getElementById("months_range").value = tr.querySelector(".column_7").textContent;
        document.getElementById("monthlyNumber").value = tr.querySelector(".column_9").textContent;
        document.getElementById("monthlyRange").value = tr.querySelector(".column_9").textContent;
        document.getElementById("firstName").value = tr.querySelector(".column_2").textContent;
        document.getElementById("lastName").value = tr.querySelector(".column_3").textContent;
        document.getElementById("edit_birth_date").value = tr.querySelector(".column_11").textContent;
        document.getElementById("edit_hiring_date").value = tr.querySelector(".column_17").textContent;
        document.getElementById("cin").value = tr.querySelector(".column_4").textContent;
        document.getElementById("edit_income").value = tr.querySelector(".column_8").textContent;
    })
})
document.getElementById("overlay_update").addEventListener("click" , () => {
    document.getElementById("wrapper_form").classList.add("hidden");
})


const steps = document.querySelectorAll(".step");
const indicators = document.querySelectorAll(".part");
const nextBtn = document.getElementById("nextBtn_update");
const prevBtn = document.getElementById("prevBtn_update")
const submitBtn = document.getElementById("submit_update")

function handleStep(direction) {
    currentStep += direction;

    if (currentStep >= steps.length) {
        currentStep = steps.length - 1;
        return false;
    }
    if (currentStep < 0) {
        currentStep = 0;
        return false;

    }

    steps.forEach(step => step.style.display = 'none');
    steps[currentStep].style.display = 'block';

    indicators.forEach((indicator, index) => {
        indicator.classList.remove("active", "passed");
        if (index < currentStep) {
            indicator.classList.add("passed");
        } else if (index === currentStep) {
            indicator.classList.add("active");
        }
    });

    if (currentStep === 0) {
        prevBtn.classList.add("inactive");
    } else {
        prevBtn.classList.remove("inactive");
    }

    if (currentStep === steps.length - 1) {
        nextBtn.style.display = 'none';
        submitBtn.style.display = 'block'
    }else {
        nextBtn.style.display = 'block';
        submitBtn.style.display = 'none'
    }
}


nextBtn.addEventListener("click" , () => handleStep(1))
prevBtn.addEventListener("click" , () => handleStep(-1))
submitBtn.addEventListener("click" , async (e) => {
    e.preventDefault();
    const formData = new FormData(document.getElementById("update_form"))
    const data = await doUpdate(formData);

    if (data.status === 200) {
        document.getElementById("wrapper_form").classList.add("hidden");
        showToast(data.status , data.message , "/liste")
    }
})



