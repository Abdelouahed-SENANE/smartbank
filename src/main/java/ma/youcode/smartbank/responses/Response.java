package ma.youcode.smartbank.responses;


import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.ConstraintViolation;
import ma.youcode.smartbank.dtos.RequestFilterDTO;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Response<T> {

    public Response() {}

    public static <T> String success(String message , int status ,List<Optional<T>> data) {
        JsonArrayBuilder dataArray = Json.createArrayBuilder();
        if (data != null) {
            for (Optional<T> optionalItem : data) {
                if (optionalItem.isPresent()) {
                     T entity = optionalItem.get();
                    JsonObject requestJson = buildJsonFromEntity(entity);
                    dataArray.add(requestJson);
                }
            }
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


    private static <T> JsonObject buildJsonFromEntity(T entity) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            try {
                Object value = field.get(entity);
                if (value != null) {
                    if (value instanceof String || value instanceof Number) {
                        jsonBuilder.add(field.getName(), value.toString());
                    } else if (value instanceof java.util.Date) {
                        jsonBuilder.add(field.getName(), ((Date) value).toString());
                    } else {
                        jsonBuilder.add(field.getName(), value.toString());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return jsonBuilder.build();
    }
}
