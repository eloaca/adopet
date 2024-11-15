package com.eloaca.adopet.core.ports.validacao

import com.eloaca.adopet.adapters.domain.dto.SolicitacaoAdocaoDto

interface AdocaoValidacao {

    fun validar(solicitacao : SolicitacaoAdocaoDto)
}