package com.eloaca.adopet.adapters.controller.dto

import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import com.fasterxml.jackson.annotation.JsonProperty

class SolicitacaoTutorDto (

    @JsonProperty("nome")
    val nome: String,

    @JsonProperty("tipo")
    val tipo: TipoDocumento,

    @JsonProperty("documento")
    val documento: String
)