package com.eloaca.adopet.adapters.controller.rs

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.domain.dto.TutorDto
import com.eloaca.adopet.adapters.service.TutorService
import com.eloaca.adopet.core.ports.service.TutorPort
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tutores")
class TutorController(val service: TutorPort) {

    @PostMapping
    fun salvaNovoTutor(@RequestBody solicitacao: DataDto<SolicitacaoTutorDto>) : ResponseEntity<DataDto<TutorDto>> {
        return ResponseEntity(service.salvarNovoTutor(solicitacao.data), HttpStatus.CREATED)
    }
}