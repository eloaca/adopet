package com.eloaca.adopet.adapters.controller.rs

import com.eloaca.adopet.adapters.domain.dto.AdocaoDto
import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.controller.dto.SolicitacaoAdocaoDto
import com.eloaca.adopet.core.ports.service.AdocaoPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping
    fun consultaAdocoes(@RequestParam(name = "id_tutor", required = true) idTutor : Long) : ResponseEntity<DataDto<List<AdocaoDto>>> {
        return ResponseEntity.ok(service.consultarAdocoes(idTutor))
    }

    @PostMapping("/aprovar")
    fun aprovarAdocoes() : ResponseEntity<Any> {
        service.adotarTodosPets()
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/aprovar/{id_adocao}")
    fun aprovarAdocao(@PathVariable(name = "id_adocao", required = true) idAdocao : Long) : ResponseEntity<DataDto<AdocaoDto>> {
        return ResponseEntity.ok(service.adotarPet(idAdocao))
    }
}