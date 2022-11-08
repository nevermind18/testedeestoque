package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Login {
    
    private String senha;
    private String login;
}
