package br.edu.ifrs.restinga.MeuMercadoTarefa.securety.service;

import java.time.LocalDateTime;
import java.util.Date;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {
    
    public String gerarToken(Authentication authentication) {
        var data = new Date();
        var dataFinal = new Date(data.getTime() + Long.parseLong("50000000000000"));
        var usuarioLogado = (Admin) authentication.getPrincipal();
        return Jwts.builder().setIssuer("MeuMerdadoTarefa")
            .setSubject(usuarioLogado.getIdAdmin().toString())
            .setIssuedAt(data)
            .setExpiration(dataFinal)
            .signWith(SignatureAlgorithm.HS256, "testando")
            .compact();
    }
    
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey("testando").parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
        
    }
    
    public Integer getIdAdmin(String token) {
        Claims claims = Jwts.parser().setSigningKey("testando").parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}
