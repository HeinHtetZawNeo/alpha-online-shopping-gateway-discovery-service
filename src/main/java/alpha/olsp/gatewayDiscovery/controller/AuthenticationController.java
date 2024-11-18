package alpha.olsp.gatewayDiscovery.controller;

import alpha.olsp.gatewayDiscovery.dto.AuthenticationResponse;
import alpha.olsp.gatewayDiscovery.dto.UserSvcAuthAuthenticateResponseDto;
import alpha.olsp.gatewayDiscovery.service.AuthenticationService;
import alpha.olsp.gatewayDiscovery.service.UserSvcService;
import alpha.olsp.gatewayDiscovery.dto.RegisterRequest;
import alpha.olsp.gatewayDiscovery.dto.AuthenticationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserSvcService userSvcService;

    @PostMapping("/register")
    public ResponseEntity<UserSvcAuthAuthenticateResponseDto> register(@RequestBody RegisterRequest registerRequest) {
        //AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);
        UserSvcAuthAuthenticateResponseDto temp = userSvcService.authenticate(new AuthenticationRequestDto("customerA@gmail.com","mypassword1"));

        return ResponseEntity.status(HttpStatus.OK).body(temp);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
