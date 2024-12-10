package com.eloaca.adopet.adapters.service

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoPetDto
import com.eloaca.adopet.adapters.datastore.entity.PetEntity
import com.eloaca.adopet.adapters.domain.enums.TipoPet
import com.eloaca.adopet.core.ports.datastore.AdocaoRepository
import com.eloaca.adopet.core.ports.datastore.PetRepository
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class PetServiceTest {

    private val repository = mockk<PetRepository>()
    private lateinit var service: PetService

    @BeforeEach
    fun setUp() {
        service = PetService(repository)
    }

    @Test
    @DisplayName("Deve consultar os pets disponiveis para adotar")
    fun consultarPetsPorAdocao() {
        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        every { repository.findByAdotado(false) } returns(listOf(pet))
        val pets = service.consultarPetsPorAdocao(false)

        assertEquals(false, pets.data[0].adotado)
    }

    @Test
    @DisplayName("Deve consultar os pets indisponiveis para adotar")
    fun consultarPetsNaoPorAdocao() {
        val pet = PetEntity (solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO))
        pet.adotado = true
        every { repository.findByAdotado(true) } returns(listOf(pet))
        val pets = service.consultarPetsPorAdocao(true)

        assertEquals(true, pets.data[0].adotado)
    }

    @Test
    @DisplayName("Deve cadastrar um novo pet no sistema com sucesso")
    fun cadastrarPet() {
        val solicitacao = SolicitacaoPetDto("PIPOCA", TipoPet.CACHORRO)
        val pet = PetEntity (solicitacao)
        every { repository.save(pet) } returns(pet)
        val cadastro = service.cadastrarPet(solicitacao)

        assertEquals(1, cadastro.data.id)
    }
}