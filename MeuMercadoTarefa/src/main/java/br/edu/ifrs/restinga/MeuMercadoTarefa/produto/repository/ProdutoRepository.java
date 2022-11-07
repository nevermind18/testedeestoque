package br.edu.ifrs.restinga.MeuMercadoTarefa.produto.repository;


import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findByIdProduto(Integer idProduto);

}
