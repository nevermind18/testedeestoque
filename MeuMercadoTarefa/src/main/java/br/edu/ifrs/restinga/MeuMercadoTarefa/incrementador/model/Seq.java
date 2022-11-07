package br.edu.ifrs.restinga.MeuMercadoTarefa.incrementador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Seq {
    
    @Id
    private String nome;
    private Integer valor;
}
