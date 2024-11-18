package alpha.olsp.gatewayDiscovery.service;

import alpha.olsp.gatewayDiscovery.dto.AuthenticationRequestDto;
import alpha.olsp.gatewayDiscovery.dto.UserSvcAuthAuthenticateResponseDto;
import alpha.olsp.gatewayDiscovery.util.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserSvcService {
    private final RestTemplate restTemplate;
    @Value("${service.base.url.user}")
    private String USER_SVC_HOST;

    public UserSvcAuthAuthenticateResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        String url = USER_SVC_HOST+"/api/v1/users/login";

        // Create request body
        String requestBody = JsonConverter.convertObjectToJsonString(authenticationRequestDto);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HttpEntity with request body and headers
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        try {
            // Make POST request
            ResponseEntity<UserSvcAuthAuthenticateResponseDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    UserSvcAuthAuthenticateResponseDto.class
            );

            // Check if the response is OK
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new HttpClientErrorException(response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, e.getResponseBodyAsString());
        }
    }
}
