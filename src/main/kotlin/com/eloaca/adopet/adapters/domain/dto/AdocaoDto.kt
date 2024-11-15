package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import java.time.LocalDate

class AdocaoDto(
    val id: Long,
    val tutor: TutorDto,
    val pet: PetDto,
    val status: StatusAdocao,
    val data: LocalDate) {

    constructor(entity: AdocaoEntity) : this(
        id = entity.id,
        status = entity.status,
        tutor = TutorDto(entity.tutor),
        pet = PetDto(entity.pet),
        data = entity.dataAdocao
    )
}