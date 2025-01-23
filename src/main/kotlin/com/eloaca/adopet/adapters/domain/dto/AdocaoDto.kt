package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class AdocaoDto(
    val id: Long,
    val tutor: TutorDto,
    val pet: PetDto,
    val status: StatusAdocao,

    @JsonProperty("data_hora_solicitacao")
    val dataHoraSolicitacao: LocalDateTime) {

    constructor(entity: AdocaoEntity) : this(
        id = entity.id,
        status = entity.status,
        tutor = TutorDto(entity.tutor),
        pet = PetDto(entity.pet),
        dataHoraSolicitacao = entity.dataHoraSolicitacao
    )
}