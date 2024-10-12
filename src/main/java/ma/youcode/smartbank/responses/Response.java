package ma.youcode.smartbank.responses;


import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.ConstraintViolation;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Request;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Response<T> {

    public Response() {}

    public static  String success(String message , int status ,List<Optional<Request>> data) {
        JsonArrayBuilder dataArray = Json.createArrayBuilder();
        if (data != null) {
            data.stream()
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(request -> requestToJson(request))
                    .forEach(dataArray::add);
        }

        JsonObject buildJson = Json.createObjectBuilder()
                .add("message", message)
                .add("status", status)
                .add("data", dataArray.build())
                .build();

        return buildJson.toString();
    }
    public static <T> String error(String message, int status, Set<ConstraintViolation<T>> violations) {
        JsonArrayBuilder dataArray = Json.createArrayBuilder();
        if (violations != null) {
            List<String> messages = violations.stream().map(ConstraintViolation::getMessage).toList();

            for ( String error : messages) {
                dataArray.add(error);
            }
        }

        JsonObject buildJson = Json.createObjectBuilder()
                .add("message", message)
                .add("status", status)
                .add("data", dataArray.build())
                .build();

        return buildJson.toString();
    }

    public static JsonObject requestToJson(Request request) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        jsonBuilder.add("requestId", request.getRequestId().toString());
        jsonBuilder.add("projectName", request.getProjectName());
        jsonBuilder.add("jobName", request.getJobName());
        jsonBuilder.add("amount", request.getAmount());
        jsonBuilder.add("civility", request.getCivility());
        jsonBuilder.add("duration", request.getDuration());
        jsonBuilder.add("monthly", request.getMonthly());
        jsonBuilder.add("email", request.getEmail());
        jsonBuilder.add("phone", request.getPhone());
        jsonBuilder.add("lastname", request.getLastname());
        jsonBuilder.add("firstname", request.getFirstname());
        jsonBuilder.add("cin", request.getCin());
        jsonBuilder.add("birthday", request.getBirthday().toString());
        jsonBuilder.add("dateOfHire", request.getDateOfHire().toString());
        jsonBuilder.add("income", request.getIncome());
        jsonBuilder.add("fees", request.getFees());

        if (request.getStatusHistories() != null) {
            JsonArrayBuilder historiesArray = Json.createArrayBuilder();
            for (History history : request.getStatusHistories()) {
                historiesArray.add(historyToJson(history));
            }
            jsonBuilder.add("histories", historiesArray);
        }

        return jsonBuilder.build();
    }

    private static JsonObject historyToJson(History history) {

        return Json.createObjectBuilder()
                .add("historyId", history.getHistoryId().toString())
                .add("status", history.getStatus().toString())
                .add("request", history.getRequest().toString())
                .add("changedAt", history.getChangedAt().toString())
                .build();
    }

}
