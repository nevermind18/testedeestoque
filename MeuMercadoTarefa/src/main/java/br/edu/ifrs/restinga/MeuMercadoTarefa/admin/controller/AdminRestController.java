package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.controller;

import java.util.List;
import javax.validation.Valid;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.service.AdminService;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/admin")
public class AdminRestController {
    
    private final AdminService adminService;
    
    @PostMapping(value = "/salvar")
    public Admin salvarProduto(
        @RequestBody
        @Valid
        Admin admin) {
        return adminService.salvarProduto(admin);
    }
    
    @GetMapping(value = "/buscartodos")
    public List<Admin> buscarTodos() {
        return adminService.buscarTodos();
    }
    
    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<Admin> buscarPorId(
        @PathVariable
        Integer id) throws Exception {
        return adminService.buscarPorId(id);
    }
    
    @PutMapping(value = "/alterar/{id}")
    public ResponseEntity<Admin> alterar(
        @PathVariable
        Integer id,
        @RequestBody
        @Valid
        Admin admin) {
        return adminService.alterar(id, admin);
    }
    
    @DeleteMapping(value = "/deletar/{id}")
    public ResponseEntity<Admin> deletar(
        @PathVariable
        Integer id) {
        return adminService.deletar(id);
    }
}
