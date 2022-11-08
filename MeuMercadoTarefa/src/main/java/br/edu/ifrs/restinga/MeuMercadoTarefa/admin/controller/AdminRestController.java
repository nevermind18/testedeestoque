package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.controller;

import java.util.List;
import javax.validation.Valid;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Login;
import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Token;
import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.service.AdminService;
import br.edu.ifrs.restinga.MeuMercadoTarefa.securety.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/admin")
public class AdminRestController {
    
    private final AdminService adminService;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    private final TokenService tokenService;
    
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
    
    @PostMapping(value = "/auth")
    public ResponseEntity<Token> autenticacao(
        @RequestBody
        @Valid Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(login.getLogin(), login.getSenha());
        try {
            var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            var token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new Token(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
        
    }
}
