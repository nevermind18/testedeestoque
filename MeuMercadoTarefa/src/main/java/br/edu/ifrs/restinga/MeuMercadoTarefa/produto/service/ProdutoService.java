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
        produto.setCodigo(seq.getValor());
        sequenciaRepository.save(seq);
        return produtoRepository.save(produto);
    }
    
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }
    
    public ResponseEntity<Produto> buscarPorId(Integer id) {
        
        var produto = produtoRepository.findByCodigo(id);
        
        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }
    
    public ResponseEntity<Produto> alterar(Integer id, Produto produtoNew) {
        
        var produtoOld = produtoRepository.findByCodigo(id);
        
        if (Objects.isNull(produtoOld)) {
            return ResponseEntity.notFound().build();
        }
        
        produtoNew.setId(produtoOld.getId());
        produtoNew.setCodigo(produtoOld.getCodigo());
        
        return ResponseEntity.ok(produtoRepository.save(produtoNew));
    }
    
    public ResponseEntity<Produto> alterarEstoque(Integer id, Integer estoque) {
        
        var produtoOld = produtoRepository.findByCodigo(id);
        
        if (Objects.isNull(produtoOld)) {
            return ResponseEntity.notFound().build();
        }
    
        produtoOld.setEstoque(estoque);
        
        return ResponseEntity.ok(produtoRepository.save(produtoOld));
    }
    
    public ResponseEntity<Produto> alterarValor(Integer id, Double valor) {
        
        var produtoOld = produtoRepository.findByCodigo(id);
        
        if (Objects.isNull(produtoOld)) {
            return ResponseEntity.notFound().build();
        }
        
        produtoOld.setValor(valor);
        
        return ResponseEntity.ok(produtoRepository.save(produtoOld));
    }
    
    public ResponseEntity<Produto> deletar(Integer id) {
        var produto = produtoRepository.findByCodigo(id);
        
        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(produto.getId());
        return ResponseEntity.ok().build();
    }
    
    public ResponseEntity<List<Produto>> buscarPorNome(String nome) {
        
        var produto = produtoRepository.findByNome(nome);
        
        return ResponseEntity.ok(produto);
    }
    
    public ResponseEntity<List<Produto>> buscarPorTipo(String tipo) {
        
        var produto = produtoRepository.findByTipo(tipo);
        return ResponseEntity.ok(produto);
    }
}
