package alpha.olsp.gatewayDiscovery.dto;


import alpha.olsp.gatewayDiscovery.user.Role;

public record RegisterRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {}