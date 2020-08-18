package com.kael.websocket.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/18 0018
 */
public class JwtUtil {
    public static String createJwt(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*12))
                .signWith(SignatureAlgorithm.HS512,"kael")
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("kael").parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }
}
