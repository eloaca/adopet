package com.eloaca.adopet.core.ports.service

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoPetDto
import com.eloaca.adopet.adapters.domain.dto.PetDto

interface PetPort {

    fun consultarPetsPorAdocao(adotado: Boolean) : DataDto<List<PetDto>>

    fun cadastrarPet(solicitacao: SolicitacaoPetDto) : DataDto<PetDto>
}