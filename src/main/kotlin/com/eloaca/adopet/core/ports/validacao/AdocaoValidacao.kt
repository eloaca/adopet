package com.eloaca.adopet.core.ports.validacao

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto

interface AdocaoValidacao {

    fun validar(solicitacao : SolicitacaoAdocaoDto)
}