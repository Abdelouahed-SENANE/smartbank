const request = async (url, method, payload) => {
    try {
        const response = await fetch(url, {
            method: method.toUpperCase(),
            headers : {
                'Accept' : 'application/json'
            },
            body: payload,
        })

        if (!response.ok) {
            return
        }
        return response.json();
    } catch (e) {
        return e
    }

}

export const doSave = async (payload) => {

    try {
        const data = await request("http://localhost:8080/demandes/save", "POST", payload)
        return data
    } catch (e) {
        return e
    }

}
export const getFilteredRequests = async (payload) => {

    try {
        const data = await request(`http://localhost:8080/demandes/filter?name=${payload.name}&creationDate=${payload.creationDate}`, "GET", )
        return data
    } catch (e) {
        console.log(e)
    }

}
export const doDelete = async (requestId) => {

    try {
        const data = await request(`http://localhost:8080/demandes/delete?requestId=${requestId}`, "POST" )
        return data
    } catch (e) {
        console.log(e)
    }

}
export const doUpdate = async (payload) => {

    try {
        const data = await request(`http://localhost:8080/demandes/update`, "POST", payload);
        return data;
    } catch (e) {
        console.log(e);
        return null;
    }

}
export const doSaveHistory = async (payload) => {

    try {
        const data = await request(`http://localhost:8080/demandes/save-history`, "POST", payload)
        console.log(data)
        return data;
    } catch (e) {
        console.log(e);
        return null;
    }

}


