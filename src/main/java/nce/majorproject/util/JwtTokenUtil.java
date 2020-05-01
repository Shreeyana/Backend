//package nce.majorproject.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import nce.majorproject.exception.RestException;
//import org.springframework.stereotype.Component;
//
//
//import java.util.Date;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtTokenUtil {
//    private static final Long serialVerisionUid= -2550185165626007488L;
//    private String secret = "MWFoMEc3YmpkaHY=";
//
//    public String getUserNameFromToken(String token){
//        return getClaimFromToken(token,Claims::getSubject);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
//    final Claims claims=getAllClaimsFromToken(token);
//    return claimResolver.apply(claims);
//
//    }
//    public String getByKey(String token, String key) {
//        Claims allClaimsFromToken = getAllClaimsFromToken(token);
//        return (String) allClaimsFromToken.get(key);
//    }
//
//    private Claims getAllClaimsFromToken(String token){
//        try {
//            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        }catch (Exception e){
//            throw new RestException("Invalid token");
//        }
//    }
//
//    public String generateToken(Map<String,Object> claims){
//        return doGenerateToken(claims,claims.get("userName").toString());
//    }
//
//    private String doGenerateToken(Map<String,Object> claims,String subject){
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .signWith(SignatureAlgorithm.HS256,secret).compact();
//    }
//}
