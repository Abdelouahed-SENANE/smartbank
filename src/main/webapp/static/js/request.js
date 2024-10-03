


export const post = (url , method , payload) => {

    fetch(url, {
        method: method.toUpperCase(),
        body: payload,  // Convert the object to JSON string
    })
        .then(response => response.json())  // Convert the response to JSON
        .then(data => console.log(data))    // Log the received data
        .catch(error => console.error('Error:', error));
}