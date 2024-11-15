package com.eloaca.adopet.core.ports.service

import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.domain.dto.DataDto
import com.eloaca.adopet.adapters.domain.dto.SolicitacaoAdocaoDto

interface AdocaoPort {

   fun salvarNovaAdocao(solicitacao: SolicitacaoAdocaoDto) : DataDto<AdocaoDto>

   fun consultarAdocoes(idTutor: Long) : DataDto<List<AdocaoDto>>

}