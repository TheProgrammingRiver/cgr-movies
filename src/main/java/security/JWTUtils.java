package security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JWTUtils {
    Logger logger = Logger.getLogger(JWTUtils.class.getName());
    @Value("${jwt-secret}")
    public String jwtSecret;

    @Value("${jwt-expiration-ms}")
    public int jwtExpMS;

    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    

}
