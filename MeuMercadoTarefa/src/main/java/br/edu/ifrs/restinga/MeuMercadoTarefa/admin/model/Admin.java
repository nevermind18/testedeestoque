package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Document
public class Admin implements UserDetails {
    
    public final static String sequencia = "admin";
    
    @Id
    private String id;
    private Integer idAdmin;
    
    @NotNull
    @Length(min = 5,max = 25, message = "Login deve ter entre 5 e 25 letras")
    private String login;
    @NotNull
    @NotEmpty
    @Length(min = 5,max = 10, message = "senha deve ter entre 5 e 10 letras")
    private String senha;
    @Length(min = 5,max = 10, message = "nome deve ter entre 5 e 10 letras")
    @NotNull
    @NotEmpty
    private String nome;
    
    private List<Perfil> perfils = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfils;
    }
    
    @Override
    public String getPassword() {
        return this.senha;
    }
    
    @Override
    public String getUsername() {
        return this.login;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
