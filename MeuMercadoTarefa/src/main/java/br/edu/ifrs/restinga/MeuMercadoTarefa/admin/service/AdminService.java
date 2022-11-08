package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.service;

import java.util.List;
import java.util.Objects;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.repository.AdminRepository;
import br.edu.ifrs.restinga.MeuMercadoTarefa.incrementador.repository.SequenciaRepository;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    
    private final AdminRepository adminRepository;
    
    private final SequenciaRepository sequenciaRepository;
    
    public Admin salvarProduto(Admin admin) {
        var seq = sequenciaRepository.findByNome(admin.sequencia);
        seq.setValor(seq.getValor() + 1);
        admin.setIdAdmin(seq.getValor());
        sequenciaRepository.save(seq);
        admin.setSenha(new BCryptPasswordEncoder().encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    
    public List<Admin> buscarTodos() {
        return adminRepository.findAll();
    }
    
    public ResponseEntity<Admin> buscarPorId(Integer id) {
        
        var admin = adminRepository.findByIdAdmin(id);
        
        if (Objects.isNull(admin)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }
    
    public ResponseEntity<Admin> alterar(Integer id, Admin adminNew) {
        
        var adminOld = adminRepository.findByIdAdmin(id);
        
        if (Objects.isNull(adminOld)) {
            return ResponseEntity.notFound().build();
        }
    
        adminNew.setId(adminOld.getId());
        adminNew.setIdAdmin(adminOld.getIdAdmin());
        
        return ResponseEntity.ok(adminRepository.save(adminNew));
    }
    
    public ResponseEntity<Admin> deletar(Integer id) {
        var produto = adminRepository.findByIdAdmin(id);
        
        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }
        adminRepository.deleteById(produto.getId());
        return ResponseEntity.ok().build();
    }
}
