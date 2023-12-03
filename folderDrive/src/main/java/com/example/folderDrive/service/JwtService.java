package com.example.folderDrive.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtService {
    public String extractUsername(String token) {
        return  null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
}
