package com.namndt.springbootjooqupdate.config.authConfig;

import com.namndt.springbootjooqupdate.data.dto.UserDTO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    private final String JWT_SECRET = "abcdefghJKLMNO123";

    private final Long EXPIRATION = Long.valueOf(24 * 60 * 60 * 1000);

    public String generateToken(UserDTO userDetails){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("JWT Token expired");
        } catch (MalformedJwtException malformedJwtException) {
            log.error("JWT Token invalid");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("JWT Token unsupported");
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("");
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
