export function showToast(status , message , path) {
    const toast = document.getElementById("toast_wrapper")
    const toastCard = document.getElementById("toast_card")
    const toastMessage = document.getElementById("toast_message")
    const toastTitle = document.getElementById("toast_title")
    toast.classList.remove("hidden")
    toast.classList.add("visible")
    toastMessage.innerText = message;
    switch (status) {
        case 200:
            toastCard.classList.add("success")
            toastCard.classList.remove("failed")
            toastTitle.innerText = "Succès"
            break
        case 401:
            toastCard.classList.remove("success")
            toastCard.classList.add("failed")
            toastTitle.innerText = "Échec"
            break
        default:
            toastCard.classList.remove("success", "failed");
            toastTitle.innerText = "Notification";
            break;
    }

    toast.classList.remove("hidden");
    toast.classList.add("visible");

    setTimeout(() => {
        toast.classList.remove("visible");
        toast.classList.add("hidden");
        window.location.href = `http://localhost:8080/demandes/${path}`
    }, 5000);


}