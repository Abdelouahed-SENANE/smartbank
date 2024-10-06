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

export const save = async (payload) => {

    try {
        const data = await request("http://localhost:8080/demandes/save", "POST", payload)
        return data
    } catch (e) {
        return e
    }

}