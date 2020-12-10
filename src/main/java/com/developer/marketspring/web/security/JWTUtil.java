package com.developer.marketspring.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String Key= "d3v3l0p3r";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder() // Permite construir un json web token en una secuencia de metodos
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, Key) // firmar nuestro algoritmo
                .compact(); // finalizar y crear nuestro jwt
    }

    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(pullUsername(token))
                && !expiredToken(token);
    }

    public String pullUsername(String token){
        return getClaims(token).getSubject(); // getSubject esta el usuario de la peticion
    }

    public boolean expiredToken(String token){
        return getClaims(token).getExpiration().before(new Date());
        // before para saber si el token esta antes de la fecha actual o si ya expiro
    }

    private Claims getClaims(String token){ // verificar que la firma sea valida
        return Jwts.parser().setSigningKey(Key) //Se añade la llave de la firma al parser y despues de verificar
                .parseClaimsJws(token).getBody(); // Obtiene el cuerpo de mi jwt separado por cada objeto
    }
}
