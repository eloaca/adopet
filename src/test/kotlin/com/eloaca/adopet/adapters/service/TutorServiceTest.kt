package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.datastore.entity.TutorEntity
import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.validacao.TutorValidacao
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class TutorServiceTest {

    private val repository = mockk<TutorRepository>()
    private lateinit var service: TutorService

    @SpyK
    private val validacoes: List<TutorValidacao> = ArrayList()

    @BeforeEach
    fun setUp() {
        service = TutorService(repository, validacoes)
    }

    @Test
    @DisplayName("Deve salvar um novo tutor no sistema com sucesso")
    fun salvarNovoTutor() {
        val solicitacao = SolicitacaoTutorDto("JOAO", TipoDocumento.CPF, "123456789")
        val tutor = TutorEntity(solicitacao)
        every { repository.save(any()) } returns tutor
        val cadastrado = service.salvarNovoTutor(solicitacao)
        assertEquals(1L, cadastrado.data.id)
    }
}