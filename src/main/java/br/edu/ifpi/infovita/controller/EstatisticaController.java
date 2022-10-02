package br.edu.ifpi.infovita.controller;

import br.edu.ifpi.infovita.client.Sincronizador;
import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.dto.EstatisticaDto;
import br.edu.ifpi.infovita.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticaService estatisticaService;
    private final Sincronizador sincronizador;

    @GetMapping(path = "/sync")
    public ResponseEntity<Void> sincronizarPorMunicipio(@RequestParam String municipio){
        sincronizador.sincronizarPorMunicipio(municipio);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/equipamento/{codEquipamento}")
    public ResponseEntity<EstatisticaDto> findByEquipamento(@PathVariable Long codEquipamento){
        return new ResponseEntity<>(
                estatisticaService.findByEquipamento(Equipamento.builder().cod(codEquipamento).build()), HttpStatus.OK);
    }

    @GetMapping(path = "/equipamento/all")
    public ResponseEntity<List<EstatisticaDto>> findAll(){
        return new ResponseEntity<>(estatisticaService.findAll(), HttpStatus.OK);
    }
}
