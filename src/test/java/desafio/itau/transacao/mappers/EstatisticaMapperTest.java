package desafio.itau.transacao.mappers;

import desafio.itau.transacao.dtos.EstatisticaResponse;
import desafio.itau.transacao.entities.Estatistica;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstatisticaMapperTest {

    @Test
    void deveriaRetornarUmDTOEstatistica() {
        // ARRANGE
        Estatistica estatistica = new Estatistica(1, 200, 200, 200, 200);

        // ACT
        EstatisticaResponse estatisticaResponse = EstatisticaMapper.toResponse(estatistica);

        // ASSERT
        Assertions.assertEquals(1, estatisticaResponse.count());
        Assertions.assertEquals(200, estatisticaResponse.sum());
        Assertions.assertEquals(200, estatisticaResponse.avg());
        Assertions.assertEquals(200, estatisticaResponse.min());
        Assertions.assertEquals(200, estatisticaResponse.max());
    }
}