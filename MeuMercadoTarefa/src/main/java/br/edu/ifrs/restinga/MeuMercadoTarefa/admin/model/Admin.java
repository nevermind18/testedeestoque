package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {
    
    public final static String sequencia = "admin";
    
    @Id
    private String id;
    private Integer idAdmin;
    
    @NotNull
    @Length(min = 5,max = 25, message = "Login deve ter entre 5 e 25 letras")
    private double login;
    @NotNull
    @NotEmpty
    @Length(min = 5,max = 10, message = "Login deve ter entre 5 e 10 letras")
    private String senha;
    @Min(0)
    @NotNull
    private Integer nome;
}
