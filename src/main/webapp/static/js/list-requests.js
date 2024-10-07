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

const btnDisplay = document.getElementById("date-icon");
