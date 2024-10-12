import {getFilteredRequests, doDelete, doSaveHistory} from "./requests.js";
import {showToast} from "./globals.js";

const viewsButton = document.querySelectorAll(".view");
const overlaysTable = document.querySelectorAll(".overlay_table");

viewsButton.forEach((e, i) =>
    e.addEventListener("click", () => toggleHistories(i))
);

overlaysTable.forEach((e, i) =>
    e.addEventListener("click", () => toggleHistories(i))
);

function toggleHistories(index) {
    const histories = document.querySelectorAll(".histories")[index];
    if (histories.classList.contains("visible")) {
        histories.classList.remove("visible");
    } else {
        histories.classList.add("visible");
    }
}

// const btnSearch = document.getElementById("btn_search");
//
// btnSearch.addEventListener("click" ,  async (e) => {
//     console.log(e.target)
//     const textValue = document.getElementById("search-input").value;
//     const dateValue = document.getElementById("date-input").value
//     const payload = {
//         creationDate : dateValue,
//         name : textValue
//     }
//      const data = await getFilteredRequests(payload)
//     console.log(data);
// })


const btnsDel = document.querySelectorAll(".delete");
btnsDel.forEach((btn, index) => {
    btn.addEventListener("click" ,   (e) => {
        const confirmation = confirm("Are you sure to delete !")
        const requestId = btn.getAttribute("data-id")
        if (confirmation) {
            try {
                const data =  doDelete(requestId);
                e.currentTarget.closest('tr').remove();
            } catch (error) {
                console.error("Error during deletion:", error);
            }
        }
    })
})


const updateStatusBtns = document.querySelectorAll(".update_status");
document.getElementById("overlay_status").addEventListener("click" ,() => {
    document.getElementById("status_form").classList.add("hidden")
})
updateStatusBtns.forEach((el , index) => {
    el.addEventListener("click" , () => {
        const status = el.getAttribute("data-status")
        document.getElementById("status_actuel").innerText = status
        document.getElementById("status_requestId").value = el.getAttribute("data-id")
        document.getElementById("status_form").classList.remove("hidden")

    })
})

document.getElementById("submit_status").addEventListener("click" , async() => {
    const payload = new FormData(document.getElementById("update_status_form"))
    const data = await doSaveHistory(payload)
    document.getElementById("status_form").classList.add("hidden")

    showToast(200 , "Le status a ete modifier avec success" , "liste")
})