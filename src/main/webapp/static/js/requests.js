const request = async (url, method, payload) => {
    try {
        const response = await fetch(url, {
            method: method.toUpperCase(),
            body: payload,
        })

        if (!response.ok) {
            return 
        }
        return await response.json();
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
export const doDelete = async (id) => {

    try {
        const data = await request(`http://localhost:8080/demandes/delete?requestId=${id}`, "POST", )
        return data
    } catch (e) {
        console.log(e)
    }

}