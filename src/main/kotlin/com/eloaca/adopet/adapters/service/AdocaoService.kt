package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.core.exceptions.AdocaoException
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.datastore.PetRepository
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.service.AdocaoPort
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class AdocaoService (val repository: AdocaoRepository,
                     val petRepository: PetRepository,
                     val tutorRepository: TutorRepository,
                     val validacoes : List<AdocaoValidacao>) : AdocaoPort {

    val log: Logger = LoggerFactory.getLogger(AdocaoService::class.java.name)

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    override fun salvarNovaAdocao(solicitacao: SolicitacaoAdocaoDto): DataDto<AdocaoDto> {
        try {
            val pet = petRepository.getReferenceById(solicitacao.idPet)
            val tutor = tutorRepository.getReferenceById(solicitacao.idTutor)

            try {
                for (v in validacoes) {
                    v.validar(solicitacao)
                }
            } catch (e: AdocaoException) {
                log.info("Excecao lancada no metodo de validacao: ${e.message}")
                throw e
            }

            val entity = AdocaoEntity(pet, tutor)
            repository.save(entity)
            tutorRepository.save(entity.tutor)
            petRepository.save(entity.pet)

            val dto = AdocaoDto(entity)
            return DataDto(dto)
        } catch (e: AdocaoException) {
            log.info("Adocao nao registrada: ${e.message}")
            throw e
        }

    }

    override fun consultarAdocoes(idTutor: Long): DataDto<List<AdocaoDto>> {
        try {
            val entitys = repository.findByTutorId(idTutor)
            val adocoes = mutableListOf<AdocaoDto>()
            entitys.forEach{ e -> adocoes.add(AdocaoDto(e))}

            return DataDto(adocoes)
        } catch (e: AdocaoException) {
            log.info("Adocoes nao consultadas: ${e.message}")
            throw e
        }
    }
}