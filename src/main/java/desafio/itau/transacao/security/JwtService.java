package desafio.itau.transacao.security;

import desafio.itau.transacao.entities.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    private Key getChaveDeEntrada() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String gerarToken(String emailUsuario, Role role) {
        return Jwts.builder()
                .setClaims(Map.of("role", role))
                .setSubject(emailUsuario)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getChaveDeEntrada(), SignatureAlgorithm.HS256)
                .compact();
    }

    public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getChaveDeEntrada())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String extrairEmailUsuario(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public boolean tokenExpirado(String token) {
        return extrairClaim(token, Claims::getExpiration).before(new Date());
    }

    public boolean validarToken(String token, String nomeUsuario) {
        final String subject = extrairEmailUsuario(token);
        return subject.equals(nomeUsuario) && !tokenExpirado(token);
    }
}
