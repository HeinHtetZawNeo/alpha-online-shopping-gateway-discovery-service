package alpha.olsp.gatewayDiscovery.dto;

public record AuthenticationRequestDto(
        String email,
        String password
) {
}
