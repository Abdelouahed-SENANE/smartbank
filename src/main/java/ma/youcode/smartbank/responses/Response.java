package ma.youcode.smartbank.responses;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Response {
    private String message;
    private int status;

    public Response(String message, int status) {
        this.status = status;
        this.message = message;
    }

    public String responseJson() {
        JsonObject buildJson = Json.createObjectBuilder()
                .add("message", message)
                .add("status", status)
                .build();
        return buildJson.toString();
    }

}
