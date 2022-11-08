package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Data
@Document
public class Perfil implements GrantedAuthority {
    
    @Id
    private String id;
    private String nome;
    
    @Override
    public String getAuthority() {
        return this.nome;
    }
}
