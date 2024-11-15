package com.eloaca.adopet.adapters.domain.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class DataDto<T> @JsonCreator constructor(
    @JsonProperty("data") val data: T
)