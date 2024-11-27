package com.eloaca.adopet.core.ports.service

import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto

interface AdocaoPort {

   fun salvarNovaAdocao(solicitacao: SolicitacaoAdocaoDto) : DataDto<AdocaoDto>

   fun consultarAdocoes(idTutor: Long) : DataDto<List<AdocaoDto>>

   fun adotarTodosPets()

   fun adotarPet(idAdocao : Long) : DataDto<AdocaoDto>
}