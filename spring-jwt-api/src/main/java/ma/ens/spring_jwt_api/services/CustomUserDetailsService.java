package ma.ens.spring_jwt_api.services;

import ma.ens.spring_jwt_api.entities.User;
import ma.ens.spring_jwt_api.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // ⚡ Préfixe ROLE_ ajouté uniquement si nécessaire
        Collection<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> {
                    String roleName = role.getName();
                    if (!roleName.startsWith("ROLE_")) {
                        roleName = "ROLE_" + roleName;
                    }
                    return new SimpleGrantedAuthority(roleName);
                })
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isActive(),
                true, true, true,
                authorities
        );
    }
}
