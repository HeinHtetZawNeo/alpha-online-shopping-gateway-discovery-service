package alpha.olsp.gatewayDiscovery.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
    public static String convertObjectToJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
