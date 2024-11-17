package com.eloaca.adopet.core.ports.service

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.domain.dto.TutorDto

interface TutorPort {

    fun salvarNovoTutor(solicitacao: SolicitacaoTutorDto) : DataDto<TutorDto>
}