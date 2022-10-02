package br.edu.ifpi.infovita.client;

import br.edu.ifpi.infovita.client.dto.EstatisticasScrapingDto;
import br.edu.ifpi.infovita.domain.Equipamento;
import br.edu.ifpi.infovita.domain.Estatistica;
import br.edu.ifpi.infovita.domain.Municipio;
import br.edu.ifpi.infovita.service.EquipamentoService;
import br.edu.ifpi.infovita.service.EstatisticaService;
import br.edu.ifpi.infovita.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Sincronizador {

    private final EstatisticaService estatisticaService;
    private final EquipamentoService equipamentoService;
    private final MunicipioService municipioService;

    @Transactional(rollbackFor = Exception.class)
    public void sincronizarPorMunicipio(String municipio){
        Municipio municipioScraping = municipioService.findyByNome(municipio);

        RestTemplate rest = new RestTemplate();

        List<EstatisticasScrapingDto> estatisticas = rest.exchange(
                "http://localhost:8090/api/equipamentos/".concat(municipio),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EstatisticasScrapingDto>>() {}
        ).getBody();

        estatisticaService.deleteByMunicipio(municipioScraping);

        for (EstatisticasScrapingDto edto : estatisticas){
            Equipamento equipamento = Equipamento.builder()
                    .cod(edto.getId())
                    .nome(edto.getNome())
                    .build();

            Equipamento newOrRefreshequipamento = equipamentoService.save(equipamento);

            Estatistica estatisticaObtida = Estatistica.builder()
                    .id(Long.parseLong(newOrRefreshequipamento.getCod().toString() + municipioScraping.getCod().toString()))
                    .equipamento(newOrRefreshequipamento)
                    .municipio(municipioScraping)
                    .emUso(edto.getEmUso())
                    .existentes(edto.getExistentes())
                    .emUsoSus(edto.getEmUsoSus())
                    .existentesSUS(edto.getExistentesSUS())
                    .build();

            estatisticaService.save(estatisticaObtida);
        }

        System.out.println(estatisticas);
    }

}
