package br.edu.ifpi.infovita.service;

import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estatistica;
import br.edu.ifpi.infovita.domain.Municipio;
import br.edu.ifpi.infovita.dto.EstatisticaDto;
import br.edu.ifpi.infovita.repository.EstatisticaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EstatisticaService {

    private final EstatisticaRepository estatisticaRepository;
    private final EquipamentoService equipamentoService;

    public EstatisticaDto findByEquipamento(Equipamento equipamento){
        List<Estatistica> estatisticas = estatisticaRepository.findByEquipamento(equipamento);
        List<EstatisticaDto> estatisticasDto = new ArrayList<>();

        estatisticas.forEach(e -> estatisticasDto.add(
                EstatisticaDto.builder()
                        .codigo(e.getEquipamento().getCod())
                        .nome(estatisticas.get(0).getEquipamento().getNome())
                        .emUso(e.getEmUso())
                        .existentes(e.getExistentes())
                        .emUsoSus(e.getEmUsoSus())
                        .existentesSUS(e.getExistentesSUS())
                        .build()
        ));
        return estatisticasDto.get(0);
    }

    public List<EstatisticaDto> findAll(){
        List<Estatistica> estatisticas = estatisticaRepository.findAll();
        List<EstatisticaDto> estatisticasDto = new ArrayList<>();

        estatisticas.forEach(e -> estatisticasDto.add(
                EstatisticaDto.builder()
                        .codigo(e.getEquipamento().getCod())
                        .nome(e.getEquipamento().getNome())
                        .emUso(e.getEmUso())
                        .existentes(e.getExistentes())
                        .emUsoSus(e.getEmUsoSus())
                        .existentesSUS(e.getExistentesSUS())
                        .build()
        ));
        return estatisticasDto;
    }
    public Estatistica save(Estatistica estatistica){
        return estatisticaRepository.save(estatistica);
    }

    public void deleteByMunicipio(Municipio municipio){
        estatisticaRepository.deleteByMunicipio(municipio);
    }
}
