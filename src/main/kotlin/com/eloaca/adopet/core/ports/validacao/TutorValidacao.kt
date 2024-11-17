package com.eloaca.adopet.core.ports.validacao

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto

interface TutorValidacao {

    fun validar(solicitacao: SolicitacaoTutorDto)
}