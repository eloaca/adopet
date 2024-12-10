package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.eloaca.adopet.core.exceptions.AdocaoException
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.datastore.PetRepository
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.service.AdocaoPort
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class AdocaoService (private val repository: AdocaoRepository,
                     private val petRepository: PetRepository,
                     private val tutorRepository: TutorRepository,
                     private val validacoes : List<AdocaoValidacao>) : AdocaoPort {

    private val log: Logger = LoggerFactory.getLogger(AdocaoService::class.java.name)

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    override fun salvarNovaAdocao(solicitacao: SolicitacaoAdocaoDto): DataDto<AdocaoDto> {
        return novaAdocao(solicitacao)
    }

    override fun consultarAdocoes(idTutor: Long): DataDto<List<AdocaoDto>> {
        return adocoesPorTutor(idTutor)
    }

    override fun adotarTodosPets() {
        finalizarAdocoes()
    }

    override fun adotarPet(idAdocao: Long): DataDto<AdocaoDto> {
        return adotePet(idAdocao)
    }

    private fun adocoesPorTutor(idTutor: Long): DataDto<List<AdocaoDto>> {
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

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    private fun adotePet(idAdocao : Long): DataDto<AdocaoDto> {
        try {
            val adocao = repository.getReferenceById(idAdocao)
            val tutor = adocao.tutor
            val pet = adocao.pet

            try {
                for (v in validacoes) {
                    v.validar(solicitacao = SolicitacaoAdocaoDto(pet.id, tutor.id,true))
                }
            } catch (e: AdocaoException) {
                log.error("Adocao nao finalizada ${e.message}")
                adocao.status = StatusAdocao.DISPONIVEL
                repository.save(adocao)
                throw e
            }

            pet.adotado = true
            adocao.status = StatusAdocao.ADOTADO
            adocao.dataHoraAdocao = LocalDateTime.now()

            repository.save(adocao)
            petRepository.save(pet)

            log.info("Adocao finalizada. Pet adotado: ${pet.nome}")

            val dto = AdocaoDto(adocao)
            return DataDto(dto)
        } catch (e: AdocaoException) {
            log.info("Adocao nao registrada: ${e.message}")
            throw e
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    private fun novaAdocao(solicitacao: SolicitacaoAdocaoDto) : DataDto<AdocaoDto> {
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

            val entity = AdocaoEntity(pet, tutor, status = StatusAdocao.ADOCAO_EM_ANDAMENTO)
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

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Scheduled(cron = "\${cron.expression}")
    protected fun finalizarAdocoes() {
        log.info("Finalizando adocoes automaticamente")
        var adotados = 0
        try {
            val adocoesEmAndamento = repository.findByStatus(StatusAdocao.ADOCAO_EM_ANDAMENTO)
            adocoesEmAndamento.sortedBy { it.dataHoraSolicitacao }

            for (adocao in adocoesEmAndamento) {
                val tutor = tutorRepository.getReferenceById(adocao.tutor.id)
                val pet = petRepository.getReferenceById(adocao.pet.id)

                try {
                    for (v in validacoes) {
                        v.validar(solicitacao = SolicitacaoAdocaoDto(pet.id, tutor.id, true))
                    }
                } catch (e: AdocaoException) {
                    log.error("Adocao nao finalizada ${e.message}")
                    adocao.status = StatusAdocao.DISPONIVEL
                    repository.save(adocao)
                    throw e
                }

                pet.adotado = true
                adocao.status = StatusAdocao.ADOTADO
                adocao.dataHoraAdocao = LocalDateTime.now()

                repository.save(adocao)
                petRepository.save(pet)
                adotados += 1
            }
        } catch (e: AdocaoException) {
            log.info("Erro no processamento das adocoes. Verifique os logs \n")
            log.error(e.message)
            throw e
        }
        log.info("Adocoes finalizadas. Pets adotados: $adotados")
    }
}