package br.edu.ifrs.restinga.MeuMercadoTarefa.produto.service;

import java.util.List;
import java.util.Objects;

import br.edu.ifrs.restinga.MeuMercadoTarefa.incrementador.repository.SequenciaRepository;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    private final SequenciaRepository sequenciaRepository;
    
    public Produto salvarProduto(Produto produto) {
        var seq = sequenciaRepository.findByNome(produto.sequencia);
        seq.setValor(seq.getValor() + 1);
        produto.setIdProduto(seq.getValor());
        sequenciaRepository.save(seq);
        return produtoRepository.save(produto);
    }
    
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }
    
    public ResponseEntity<Produto> buscarPorId(Integer id) {
        
        var produto = produtoRepository.findByIdProduto(id);
        
        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }
    
    public ResponseEntity<Produto> alterar(Integer id, Produto produtoNew) {
        
        var produtoOld = produtoRepository.findByIdProduto(id);
        
        if (Objects.isNull(produtoOld)) {
            return ResponseEntity.notFound().build();
        }
        
        produtoNew.setId(produtoOld.getId());
        produtoNew.setIdProduto(produtoOld.getIdProduto());
        
        return ResponseEntity.ok(produtoRepository.save(produtoNew));
    }
    
    public ResponseEntity<Produto> deletar(Integer id) {
        var produto = produtoRepository.findByIdProduto(id);
        
        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(produto.getId());
        return ResponseEntity.ok().build();
    }
}
