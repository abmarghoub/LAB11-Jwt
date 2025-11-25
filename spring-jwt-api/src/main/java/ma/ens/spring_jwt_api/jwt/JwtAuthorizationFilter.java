package ma.ens.spring_jwt_api.jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1️⃣ Récupération du header Authorization
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            // 2️⃣ Extraction du token
            String token = header.substring(7);

            // ⚡ Ici on met ton code
            String username = jwtUtil.extractUsername(token);
            if (username != null && jwtUtil.validateToken(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Chargement de l'utilisateur
                UserDetails user = userDetailsService.loadUserByUsername(username);

                // Création de l'objet d'authentification avec les rôles
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                // Injection dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // 🔍 debug
                System.out.println("JWT Username: " + username);
                System.out.println("Authorities: " + user.getAuthorities());
            }
        }

        // 3️⃣ On continue la chaîne de filtres
        filterChain.doFilter(request, response);
    }

}