package TheCarRentalProject.utils;

import TheCarRentalProject.Configuration.CorsConfiguration;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    //@Value("${
    // app.jwt.secret}")
    private static final Logger logger = LoggerFactory.getLogger(CorsConfiguration.class);

    private String SECRET_KEY="MOMjsTgeWiqCrao9WmZD2snFbpyMOMjsTgeWiqCrao9WmZD2snFbpMOMjsTgeWiqCrao9WmZD2snFbpDbpq4EO9sVChVgDpePygr7F8Kja6SM";

    public String generateAccessToken(User user) {
        System.out.println(user);
        return Jwts.builder()
                .setSubject(String.format("%s", user.getUsername()))
                .setIssuer("CodeJava")
                .claim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        logger.info("TEst");
        logger.info(String.valueOf(Jwts.parser()
               .setSigningKey(SECRET_KEY)
               .parseClaimsJws(token)
               .getBody()));
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}