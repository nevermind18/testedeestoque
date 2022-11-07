package br.edu.ifrs.restinga.MeuMercadoTarefa.incrementador.repository;


import br.edu.ifrs.restinga.MeuMercadoTarefa.admin.model.Admin;
import br.edu.ifrs.restinga.MeuMercadoTarefa.incrementador.model.Seq;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenciaRepository extends MongoRepository<Seq, String> {
    
    Seq findByNome(String nome);

}
