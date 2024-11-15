package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import java.time.LocalDate

class AdocaoDto(
    val id: Long,
    val nome: String,
    val status: StatusAdocao,
    val data: LocalDate) {

}