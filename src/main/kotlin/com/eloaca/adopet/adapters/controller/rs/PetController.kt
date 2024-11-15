package com.eloaca.adopet.adapters.controller.rs

import com.eloaca.adopet.adapters.controller.dto.DataDto
import com.eloaca.adopet.adapters.domain.dto.PetDto
import com.eloaca.adopet.core.ports.service.PetPort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pets")
class PetController(
    val service: PetPort
) {

    @GetMapping
    fun consultaPetsParaAdotar(@RequestParam(name = "adotado") adotado: Boolean) : ResponseEntity<DataDto<List<PetDto>>> {
        return ResponseEntity.ok(service.consultarPetsPorAdocao(adotado))
    }


}