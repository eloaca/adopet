package com.eloaca.adopet.adapters.controller

import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.domain.dto.DataDto
import com.eloaca.adopet.adapters.domain.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.core.ports.service.AdocaoPort
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/adocoes")
class AdocaoController (
    val service: AdocaoPort
) {

    @PostMapping
    fun salvaAdocao(@RequestBody request: DataDto<SolicitacaoAdocaoDto>) : ResponseEntity<DataDto<AdocaoDto>> {
        return ResponseEntity(service.salvarNovaAdocao(request.data), HttpStatus.CREATED)
    }

    @GetMapping("/tutor/{id_tutor}")
    fun consultaAdocoes(@RequestParam idTutor : Long) : ResponseEntity<DataDto<List<AdocaoDto>>> {
        return ResponseEntity.ok(service.consultarAdocoes(idTutor))
    }
}