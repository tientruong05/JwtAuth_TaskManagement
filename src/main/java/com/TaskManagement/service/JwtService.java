package com.TaskManagement.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service responsible for handling JSON Web Token (JWT) operations.
 *
 * This includes generating, parsing, and validating tokens. This service is a core component
 * of the stateless authentication mechanism.
 */
@Service
public class JwtService {

    /**
     * The secret key used to sign and validate JWTs. Injected from configuration properties.
     */
    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * The expiration time for JWTs, in milliseconds. Injected from configuration properties.
     */
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Extracts the username (subject) from a JWT.
     *
     * @param token The JWT to process.
     * @return The username contained within the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generates a new JWT for a user without any extra claims.
     *
     * @param userDetails The user details for whom the token is to be generated.
     * @return The generated JWT string.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a new JWT for a user with additional claims.
     *
     * @param extraClaims A Map of additional claims to include in the token.
     * @param userDetails The user details (will be used as the subject).
     * @return The generated and signed JWT string.
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks if a JWT is valid.
     *
     * A token is considered valid if the username in the token matches the UserDetails
     * and the token has not expired.
     *
     * @param token       The JWT to check.
     * @param userDetails The user details to compare against.
     * @return {@code true} if the token is valid, {@code false} otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the token has expired.
     *
     * @param token The JWT to check.
     * @return {@code true} if the token has expired, {@code false} otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the token.
     *
     * @param token The JWT to process.
     * @return The expiration date of the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * A generic function to extract a specific claim from a token.
     *
     * @param token          The JWT to process.
     * @param claimsResolver A function to specify which claim to extract.
     * @param <T>            The data type of the claim to be extracted.
     * @return The extracted claim.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT payload.
     *
     * @param token The JWT to parse.
     * @return A {@link Claims} object containing all claims from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Creates and returns the signing key from the Base64 encoded secret key string.
     *
     * @return The {@link Key} signing key used for the HMAC-SHA algorithm.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
