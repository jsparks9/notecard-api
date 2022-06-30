package com.revature.notecard.service.token;


import com.revature.notecard.service.exceptions.MissingAuthTokenException;
import com.revature.notecard.service.exceptions.TokenParseException;
import com.revature.notecard.service.dtos.Principal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final JwtConfig jwtConfig;

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
    //public TokenService() {this.jwtConfig = new JwtConfig();}

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();

        JwtBuilder tokenBuilder = Jwts.builder()
                                      .setIssuer("notecard")
                                      .claim("id", ""+subject.getAuthUserId())
                                      .claim("role", subject.getAuthUserRole())
                                      .setIssuedAt(new Date(now))
                                      .setExpiration(new Date(now + jwtConfig.getExpiration()))
                                      .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());

        return tokenBuilder.compact();

    }

    public Principal extractTokenDetails(String token) {

        if (token == null || token.isEmpty()) {
            throw new MissingAuthTokenException();
        }

        try {
            Claims claims = Jwts.parser()
                                .setSigningKey(jwtConfig.getSigningKey())
                                .parseClaimsJws(token)
                                .getBody();

            return new Principal(Long.parseLong(claims.get("id", String.class)) , claims.get("role", String.class));
        } catch (ExpiredJwtException e) {
            throw new TokenParseException("The provided token is expired", e);
        } catch (Exception e) {
            throw new TokenParseException(e);
        }
    }


}
