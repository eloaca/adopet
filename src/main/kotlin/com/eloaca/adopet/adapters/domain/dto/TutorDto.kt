package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.datastore.entity.TutorEntity
import com.eloaca.adopet.adapters.domain.enums.TipoDocumento

class TutorDto(

    val id: Long,
    val nome: String,
    val tipo: TipoDocumento,
    val documento: String
) {

    constructor(entity: TutorEntity) : this(
        id = entity.id,
        nome = entity.nome,
        tipo = entity.tipoDocumento,
        documento = entity.documento
    )
}