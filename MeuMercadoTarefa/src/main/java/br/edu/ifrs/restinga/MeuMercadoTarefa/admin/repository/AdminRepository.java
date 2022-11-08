package br.edu.ifrs.restinga.MeuMercadoTarefa.admin.repository;


import java.util.Optional;

import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import br.edu.ifrs.restinga.MeuMercadoTarefa.produto.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    Admin findByIdAdmin(Integer idAdmin);
    
    Optional<Admin> findByLogin(String login);

}
