import {getFilteredRequests , doDelete} from "./requests.js";

const viewsButton = document.querySelectorAll(".view");
const overlaysTable = document.querySelectorAll(".overlay_table");
console.log(overlaysTable)
// Add click event listeners for each view button
viewsButton.forEach((e, i) =>
    e.addEventListener("click", () => toggleHistories(i))
);

overlaysTable.forEach((e, i) =>
    e.addEventListener("click", () => toggleHistories(i))
);

function toggleHistories(index) {
    const histories = document.querySelectorAll(".histories")[index]; // Get the corresponding histories element
    if (histories.classList.contains("visible")) {
        histories.classList.remove("visible");
    } else {
        histories.classList.add("visible");
    }
}

const btnSearch = document.getElementById("btn_search");

btnSearch.addEventListener("click" ,  async (e) => {
    console.log(e.target)
    const textValue = document.getElementById("search-input").value;
    const dateValue = document.getElementById("date-input").value
    const payload = {
        creationDate : dateValue,
        name : textValue
    }
     const data = await getFilteredRequests(payload)
    console.log(data);
})


const btnsDel = document.querySelectorAll(".delete");

btnsDel.forEach(btn => {
    btn.addEventListener("click" , (e) => {
        const confirmation = confirm("Are you sure to delete !")
        const requestId = btn.getAttribute("data-id")
        if (confirmation) {
            const data = doDelete(requestId)
            e.currentTarget.closest('tr').remove();
            alert("a ete delete avec success");
        }
    })
})

