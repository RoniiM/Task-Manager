package com.project.TaskManager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        // --- ADDED LOGGING ---
        log.info("AuthFilter checking request to: {}", request.getRequestURI());
        log.info("Token found in header: {}", token != null);

        if (token != null) {
            try {
                String username = jwtUtils.getUsernameFromToken(token);
                log.info("Extracted username from token: {}", username);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (jwtUtils.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("SUCCESS: User authenticated and set in SecurityContext.");
                } else {
                    log.warn("WARNING: Token validation failed (expired or invalid signature).");
                }
            } catch (Exception e) {
                // We catch exceptions HERE (during token parsing) so a bad token
                // doesn't crash the server, it just treats them as unauthenticated.
                log.error("ERROR: Failed to parse token. Reason: {}", e.getMessage());
            }
        }

        // --- REMOVED THE TRY-CATCH BLOCK ---
        // Let the request continue down the chain normally!
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String tokenWithBearer = request.getHeader("Authorization");
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7);
        }
        return null;
    }
}