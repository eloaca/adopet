package com.eloaca.adopet.adapters.controller.dto

import com.eloaca.adopet.adapters.domain.enums.TipoPet
import com.fasterxml.jackson.annotation.JsonProperty

class SolicitacaoPetDto(

    @JsonProperty("nome_pet")
    val nome: String,

    @JsonProperty("tipo_pet")
    val tipo: TipoPet
)