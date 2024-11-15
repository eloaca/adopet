package com.eloaca.adopet.adapters.validacao

import com.eloaca.adopet.adapters.domain.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.eloaca.adopet.core.exceptions.AdocaoException
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import org.springframework.stereotype.Component

@Component
class PetEmAdocaoValidacao(val repository: AdocaoRepository) : AdocaoValidacao {

    override fun validar(solicitacao: SolicitacaoAdocaoDto) {
        val adocaoEmAndamento = repository.existsByPetIdAndStatus(solicitacao.idPet, StatusAdocao.ADOCAO_EM_ANDAMENTO)
        if (adocaoEmAndamento) {
            throw AdocaoException("Pet ja esta sendo adotado")
        }
    }
}