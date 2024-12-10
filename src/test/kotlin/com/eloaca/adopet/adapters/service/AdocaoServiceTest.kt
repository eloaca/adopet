package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoPetDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.datastore.entity.PetEntity
import com.eloaca.adopet.adapters.datastore.entity.TutorEntity
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import com.eloaca.adopet.adapters.domain.enums.TipoPet
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.datastore.PetRepository
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.validacao.AdocaoValidacao
import io.mockk.Called
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Spy

class AdocaoServiceTest {

    private val repository = mockk<AdocaoRepository>()
    private val petRepository = mockk<PetRepository>()
    private val tutorRepository = mockk<TutorRepository>()

    @SpyK
    private val validacoes: List<AdocaoValidacao> = ArrayList()

    private lateinit var service: AdocaoService

    @BeforeEach
    fun init() {
        service = AdocaoService(repository, petRepository, tutorRepository, validacoes)
    }

    @Test
    @DisplayName("Deve salvar uma nova adocao com sucesso")
    fun salvarAdocaoComSucesso() {

        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        val tutor = TutorEntity(solicitacao = SolicitacaoTutorDto("JOAO", TipoDocumento.CPF, "123456789"))
        val ado = AdocaoEntity(pet, tutor, StatusAdocao.ADOCAO_EM_ANDAMENTO)

        every { petRepository.getReferenceById(1L) } returns pet
        every { tutorRepository.getReferenceById(1L) } returns tutor
        every { tutorRepository.save(any()) } returns tutor
        every { petRepository.save(any()) } returns pet
        every { repository.save(any()) } returns ado

        val adocao = service.salvarNovaAdocao(solicitacao = SolicitacaoAdocaoDto(1L, 1L, false))
        Assertions.assertEquals(StatusAdocao.ADOCAO_EM_ANDAMENTO, adocao.data.status)

    }

    @Test
    @DisplayName("Deve consultar uma adocao pelo id do tutor com sucesso")
    fun consultarAdocao() {
        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        val tutor = TutorEntity(solicitacao = SolicitacaoTutorDto("JOAO", TipoDocumento.CPF, "123456789"))
        val adocao = AdocaoEntity(pet, tutor, StatusAdocao.ADOCAO_EM_ANDAMENTO)

        every { repository.findByTutorId(1L) } returns listOf(adocao)

        val adocoes = service.consultarAdocoes(1L)
        Assertions.assertEquals(1, adocoes.data.size)
    }

    @Test
    @DisplayName("Deve adotar um novo pet com sucesso")
    fun adotarPet() {
        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        val tutor = TutorEntity(solicitacao = SolicitacaoTutorDto("JOAO", TipoDocumento.CPF, "123456789"))
        val ado = AdocaoEntity(pet, tutor, StatusAdocao.ADOCAO_EM_ANDAMENTO)

        every { repository.getReferenceById(1L) } returns ado
        every { repository.save(ado) } returns ado
        every { petRepository.save(pet) } returns pet

        val adocao = service.adotarPet(1L)
        Assertions.assertEquals(StatusAdocao.ADOTADO, adocao.data.status)

    }

    @Test
    @DisplayName("Deve adotar todos os pet com sucesso")
    fun adotarTodosOsPet() {
        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        val tutor = TutorEntity(solicitacao = SolicitacaoTutorDto("JOAO", TipoDocumento.CPF, "123456789"))
        val ado = AdocaoEntity(pet, tutor, StatusAdocao.ADOCAO_EM_ANDAMENTO)

        every { repository.findByStatus(any()) } returns listOf(ado)
        every { tutorRepository.getReferenceById(1L) } returns tutor
        every { petRepository.getReferenceById(1L) } returns pet
        every { repository.save(ado) } returns ado
        every { petRepository.save(pet) } returns pet

        service.adotarTodosPets()

        verify(atLeast = 1) { repository.findByStatus(StatusAdocao.ADOCAO_EM_ANDAMENTO) }
    }

}