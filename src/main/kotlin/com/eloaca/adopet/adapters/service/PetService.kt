package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoPetDto
import com.eloaca.adopet.adapters.datastore.entity.PetEntity
import com.eloaca.adopet.adapters.domain.dto.PetDto
import com.eloaca.adopet.core.exceptions.PetException
import com.eloaca.adopet.core.ports.datastore.PetRepository
import com.eloaca.adopet.core.ports.service.PetPort
import org.springframework.stereotype.Service

@Service
class PetService(
    val repository: PetRepository
) : PetPort {

    override fun consultarPetsPorAdocao(adotado: Boolean): DataDto<List<PetDto>> {
        try {
            val pets = mutableListOf<PetDto>()
            if (adotado) {
                val entitys = repository.findByAdotado(true)
                entitys.forEach { e -> pets.add(PetDto(e.id, e.nome, e.tipo, e.adotado)) }
            } else {
                val entitys = repository.findByAdotado(false)
                entitys.forEach { e -> pets.add(PetDto(e.id, e.nome, e.tipo, e.adotado)) }
            }
            return DataDto(pets)
        } catch (e : PetException) {
            throw e
        }
    }

    override fun cadastrarPet(solicitacao: SolicitacaoPetDto): DataDto<PetDto> {
        try {
            val entity = PetEntity(solicitacao)
            repository.save(entity)
            val pet = PetDto(entity.id, entity.nome, entity.tipo, entity.adotado)
            return DataDto(pet)
        } catch (e: PetException) {
            throw e
        }
    }
}