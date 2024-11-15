package com.eloaca.adopet.adapters.validacao

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.core.exceptions.AdocaoException
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import org.springframework.stereotype.Component

@Component
class TutorComLimiteDeAdocoesValidacao(val repository: AdocaoRepository) : AdocaoValidacao {

    override fun validar(solicitacao: SolicitacaoAdocaoDto) {
        val contagem = repository.countByTutorId(solicitacao.idTutor)

        if (contagem > 4) {
            throw AdocaoException("Tutor atingiu limite máximos permitidos de adocoes")
        }
    }
}