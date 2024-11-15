package com.eloaca.adopet.adapters.domain.dto

import com.eloaca.adopet.adapters.datastore.entity.PetEntity
import com.eloaca.adopet.adapters.domain.enums.TipoPet

class PetDto (

    val id : Long,
    val nome : String,
    val tipo: TipoPet,
    val adotado: Boolean
) {

    constructor(entity: PetEntity) : this (
        id = entity.id,
        nome = entity.nome,
        tipo = entity.tipo,
        adotado = entity.adotado
    )
}