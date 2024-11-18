package alpha.olsp.gatewayDiscovery.repository;


import alpha.olsp.gatewayDiscovery.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}