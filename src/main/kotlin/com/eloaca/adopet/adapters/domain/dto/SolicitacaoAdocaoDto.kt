package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class SolicitacaoAdocaoDto(

    @JsonProperty("id_pet")
    val idPet: Long,

    @JsonProperty("id_tutor")
    val idTutor: Long
)