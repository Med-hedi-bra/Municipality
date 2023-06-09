package com.example.mini_projet.Security;


import com.example.mini_projet.Municipality.MunicipalityRepository;
import com.example.mini_projet.User.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.stream;

@Service

public class JwtService {

    private static final String secretKey = "cd+Pr1js+w2qfT2BoCD+tPcYp9LbjpmhSMEJqUob1mcxZ7+Wmik4AYdjX+DlDjmE4yporzQ9tm7v3z/j+QbdYg==§";







    // extract username from jwt Token
    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }



    // Extract A Specific Claim from the The Token
    public <T> T extractClaim(String token,
                              Function<Claims,T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


   public String generateToken(UserDetails userDetails)
    {
        return  generateToken(new HashMap<>(), userDetails);
    }


    //Decode the Secret key used in the generation of the Token
    private Key getSignInKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    //function to extract the user role
    // from granted authorities collection
    public String roleToClaim(UserDetails userDetails)
    {
        var authorities =  userDetails.getAuthorities();


        var role = "";
        if (!authorities.isEmpty())
        {
            for (java.lang.Object Object : authorities)
            {
                role += Object.toString() + " ,";
            }

        }
        return role.substring(0, role.length()-2);

    }


//    public Claims extractRole(String token)
//    {
//        return extractAllClaims(token).get("authorities",Claims.class);
//    }







    //Generate The JWT Token, We can set Extra Claims(Informations) in a map function

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails)

    {

        var role = roleToClaim(userDetails);


        return Jwts
                .builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }




    // Checks if the Token is still valid
    public boolean isTokenValid(String token ,
                                UserDetails userDetails)
    {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) ;
    }




    // Checks the Expiration Time of the token
    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }



    // Extracts the Expiration Time from the Claims of the token
    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }



    //Extract all claims from the JWT token
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }






    // These Methods Are used To Create A cookie that Stores the Token Value

//    public ResponseCookie generateJwtCookie(UserDetails userPrincipal) {
//        String jwt = generateToken(userPrincipal);
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
//        return cookie;
//    }
//    //Generate A HttpOnly cookie that contains the jwt token
//
//
//    public ResponseCookie getCleanJwtCookie()
//    {
//        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
//        return cookie;
//    }
//
//    public String getJwtFromCookies(HttpServletRequest request)
//    {
//        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
//        if (cookie != null) {
//            return cookie.getValue();
//        } else {
//            return null;
//        }
//    }





}
