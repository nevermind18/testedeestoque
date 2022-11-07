package br.edu.ifrs.restinga.MeuMercadoTarefa.produto.controller;

import java.util.List;
import javax.validation.Valid;

import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/produto")
public class ProdutoRestController {
    
    private final ProdutoService produtoService;
    
    @PostMapping(value = "/salvar")
    public Produto salvarProduto(
        @RequestBody
        @Valid
        Produto produto) {
        return produtoService.salvarProduto(produto);
    }
    
    @GetMapping(value = "/buscartodos")
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodos();
    }
    
    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<Produto> buscarPorId(
        @PathVariable
        Integer id) throws Exception {
        return produtoService.buscarPorId(id);
    }
    
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<Produto> alterar(
        @PathVariable
        Integer id,
        @RequestBody
        @Valid
        Produto produto){
        return produtoService.alterar(id, produto);
    }
    
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Produto> deletar(
        @PathVariable
        Integer id) {
        return produtoService.deletar(id);
    }
}
