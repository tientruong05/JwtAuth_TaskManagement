package com.TaskManagement.config;

import com.TaskManagement.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * A filter that runs once per request to handle JWT-based authentication.
 * This filter intercepts incoming requests, extracts the JWT from the Authorization header,
 * validates it, and sets the user's authentication in the Spring Security context.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * The main filtering logic. This method is called for each HTTP request.
     *
     * @param request     The incoming HTTP request.
     * @param response    The outgoing HTTP response.
     * @param filterChain The filter chain to pass the request and response to.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. Check if the Authorization header is present and correctly formatted.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // If not, continue to the next filter.
            return;
        }

        // 2. Extract the JWT from the header.
        jwt = authHeader.substring(7); // "Bearer " is 7 characters.
        username = jwtService.extractUsername(jwt);

        // 3. Check if the user is not already authenticated.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 4. Load user details from the database.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // 5. Validate the token.
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // 6. If the token is valid, create an authentication token.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Credentials are not needed as we are using JWT.
                        userDetails.getAuthorities()
                );

                // 7. Set additional details for the authentication token.
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 8. Update the SecurityContextHolder with the new authentication.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Continue the filter chain.
        filterChain.doFilter(request, response);
    }
}
