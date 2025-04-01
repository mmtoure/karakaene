package com.mmt.karakaene.security;

import com.mmt.karakaene.model.User;
import com.mmt.karakaene.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserService userService;
    public Map<String, String> generate(String username){
        User user = this.userService.loadUserByUsername(username);
        return this.generateJwt(user);

    }

    private Map<String, String> generateJwt(User user) {
       final long currentTime = System.currentTimeMillis();
        final long expirationTime = System.currentTimeMillis()+30*60*1000;

        Map<String,Object> claims= Map.of(
                "nom",user.getFirstName(),
                "email",user.getEmail(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT, user.getEmail()
        );

        final String bearer=Jwts
                .builder()
                .issuedAt(new Date(currentTime))
                .expiration(new Date(expirationTime))
                .claims(claims)
                .signWith(getKey())
                .compact();
        return Map.of("bearer", bearer);
    }

    private SecretKey getKey(){
        String ENCRYPTION_KEY = "eead9005736e1d316958c593d926394f913a07034765cd77e8cb6ea2c4c638a2";
        final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String getUsername(String token) {

        Claims claims = getAllClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenExpired(String token) {
        Claims claims = getAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    private Claims getAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
