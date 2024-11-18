package alpha.olsp.gatewayDiscovery.dto;

import java.time.LocalDateTime;

public record UserSvcAdminRegisterResponseDto(
        String userId,
        String email,
        String firstName,
        String lastName,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
}
