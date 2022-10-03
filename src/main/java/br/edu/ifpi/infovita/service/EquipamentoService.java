package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Equipamento save(Equipamento equipamento){
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento findById(Long id){
        return equipamentoRepository.findById(id).orElseThrow();
    }

    public List<Equipamento> findALl(){
        return equipamentoRepository.findAll();
    }

}
