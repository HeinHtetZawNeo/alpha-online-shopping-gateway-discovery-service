package alpha.olsp.gatewayDiscovery.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
