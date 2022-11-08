package br.edu.ifrs.restinga.MeuMercadoTarefa.produto.repository;


import java.util.List;

import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findByCodigo(Integer codigo);
    
    List<Produto> findByNome(String nome);
    
    List<Produto> findByTipo(String tipo);

}
