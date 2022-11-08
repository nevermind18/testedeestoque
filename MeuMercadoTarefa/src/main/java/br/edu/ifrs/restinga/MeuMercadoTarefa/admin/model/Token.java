package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    
    private String token;
    private String tipo;

}
