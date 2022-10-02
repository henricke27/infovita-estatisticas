package br.edu.ifpi.infovita.repository;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estatistica;
import br.edu.ifpi.infovita.domain.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstatisticaRepository extends JpaRepository<Estatistica, Void> {

    List<Estatistica> findByEquipamento(Equipamento equipamento);
    void deleteByMunicipio(Municipio municipio);
}
