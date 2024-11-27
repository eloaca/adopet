package com.eloaca.adopet.adapters.validacao

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.eloaca.adopet.core.exceptions.AdocaoException
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import org.springframework.stereotype.Component

@Component
class PetJaAdotado(val repository : AdocaoRepository) : AdocaoValidacao {

    override fun validar(solicitacao: SolicitacaoAdocaoDto) {
        val adocaoEmAndamento = repository.existsByPetIdAndStatus(solicitacao.idPet, StatusAdocao.ADOTADO)
        if (adocaoEmAndamento) {
            throw AdocaoException("Pet ja foi adotado")
        }
    }
}