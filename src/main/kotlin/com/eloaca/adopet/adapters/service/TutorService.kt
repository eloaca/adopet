package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.datastore.entity.TutorEntity
import com.eloaca.adopet.adapters.domain.dto.TutorDto
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.service.TutorPort
import com.eloaca.adopet.core.ports.validacao.TutorValidacao
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TutorService(val repository: TutorRepository,
                   val validacoes: List<TutorValidacao>) : TutorPort {

    val log: Logger = LoggerFactory.getLogger(TutorService::class.java.name)

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    override fun salvarNovoTutor(solicitacao: SolicitacaoTutorDto): DataDto<TutorDto> {
        try {
            try {
                for (v in validacoes) {
                    v.validar(solicitacao)
                }
            } catch (e: RuntimeException) {
                log.info("Excecao capturada durante as validacoes")
                throw e
            }

            val entity = TutorEntity(solicitacao)
            repository.save(entity)
            val tutor = TutorDto(entity)

            return DataDto(tutor)
        } catch (e: RuntimeException) {
            throw e
        }
    }
}