package com.suleyman.authenticationapi.tokenconfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID= -255018516562001488L;

    private static final long JWT_TOKEN_AGE= 3*60*60;

    @Value("${jwt.secret}")
    private String secret;


    public String getUsernameAndPasswordFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String jwtToken, Function<Claims,T> claimsResolver) {
        final Claims claims= getAllClaimsFromToken(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String jwtToken){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
    }

    private  Boolean isTokenExpired(String jwtToken){
        final Date expire = getExpirationDateFromToken(jwtToken);
        return expire.before(new Date());
    }

    public String getUsernameFromToken(String jwtToken) {
        return getClaimFromToken(jwtToken,Claims::getSubject).split(",")[0];
    }

    public  String generateToken(UserDetails userDetails){
        Map<String,Object> claims= new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername()+","+userDetails.getPassword());
    }

    private String doGenerateToken(Map<String, Object> claims, String s) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(s)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_AGE * 1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public Boolean validateToken(String jwtToken, UserDetails userDetails){
        final String usernameAndPassword =getUsernameAndPasswordFromToken(jwtToken);
        final String[] splitUserNamePassword= usernameAndPassword.split(",");
        return !isTokenExpired(jwtToken) &&splitUserNamePassword[0].equals(userDetails.getUsername()) &&splitUserNamePassword[1].equals(userDetails.getPassword());
    }

}
