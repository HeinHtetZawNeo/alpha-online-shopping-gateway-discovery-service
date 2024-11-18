package alpha.olsp.gatewayDiscovery.service;


import alpha.olsp.gatewayDiscovery.dto.RegisterRequest;
import alpha.olsp.gatewayDiscovery.config.JwtService;
import alpha.olsp.gatewayDiscovery.dto.AuthenticationRequestDto;
import alpha.olsp.gatewayDiscovery.dto.AuthenticationResponse;
import alpha.olsp.gatewayDiscovery.user.User;
import alpha.olsp.gatewayDiscovery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        //Construct user object from registerRequest
        User user = new User(
                registerRequest.firstName(),
                registerRequest.lastName(),
                registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                registerRequest.role()
        );
        //save the user
        User registeredUser = userRepository.save(user);
        //generate token
        String token = jwtService.generateToken(registeredUser);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequestDto authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        //Now authentication is successfully
        //Next, generate token for this authenticated user
//        Principal principal = authentication.getPrincipal();
        User user = (User)authentication.getPrincipal();
//        User user = (User) userDetailsService.loadUserByUsername(authenticationRequest.username());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
