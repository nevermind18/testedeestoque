package br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Produto {
    
    public final static String sequencia = "produto";
    
    @Id
    private String id;
    private Integer idProduto;
    
    @Min(0)
    @NotNull
    private double valor;
    @NotNull
    @NotEmpty
    @Length(min = 3, message = "O nome deve ter mais de 3 letras")
    private String nome;
    @Min(0)
    @NotNull
    private Integer estoque;
}
