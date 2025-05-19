package manage.candidatTrackerBackend.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import manage.candidatTrackerBackend.dto.TokenDto;
import manage.candidatTrackerBackend.model.JwToken;
import manage.candidatTrackerBackend.model.User;
import manage.candidatTrackerBackend.services.implement.TokenService;
import manage.candidatTrackerBackend.services.interfaces.IUserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class JwtService {

    @Autowired private IUserService userService;
    @Autowired private TokenService tokenService;

    final String BEARER="bearer";

    @Value("${app.metadata.key}")
    private String encriptKey;

    public String  generateJwt(String userName){
        User user= userService.loadUserByUserName(userName);
        String jwt = generateToken(user);

        TokenDto tokenDto=new TokenDto(0,jwt,false,user);
        tokenService.setExpire(userName, true);
        tokenService.addToken(tokenDto);

        return jwt;
    }

    private String generateToken(User user) {

        final Long currentTime=System.currentTimeMillis();
        final Long endTime= currentTime + (60*60*1000)*24;

        final Map<String, Object> claims = Map.of(
            "name", user.getUsername(),
            Claims.EXPIRATION, new Date(endTime),
            Claims.SUBJECT, user.getUsername()
        );

        final String bearer = 
            Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(endTime))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
        return bearer;
    }

    private Key getKey() {
        final byte[] decoder = Decoders.BASE64.decode(encriptKey);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    boolean isTokenExpired(String token) {
        Date expirationDate = extractExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }
   

    private Date extractExpirationDateFromToken(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> function) {
        Claims claims = extractAllClaims(token);
        return function.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void logout() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwToken jwtoken = tokenService.findByUserUsernameAndExpire(user.getUsername(), false).get();
        jwtoken.setExpire(true);
        tokenService.updateToken(jwtoken);                       
    }

    @Scheduled(cron = "0 */10 * * * *") 
    @Transactional
    public void removeAllExpire(){
        tokenService.deleteAllExpire(true);
    }
}
