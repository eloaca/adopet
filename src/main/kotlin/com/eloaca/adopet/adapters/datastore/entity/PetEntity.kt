package com.eloaca.adopet.adapters.datastore.entity

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoPetDto
import com.eloaca.adopet.adapters.domain.dto.PetDto
import com.eloaca.adopet.adapters.domain.enums.TipoPet
import jakarta.persistence.*

@Entity
@Table(name = "pets")
data class PetEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @Enumerated(EnumType.STRING)
    val tipo: TipoPet,

    val adotado: Boolean,

//    @OneToOne(fetch = FetchType.LAZY)
//    val tutor: TutorEntity,
//
//    @OneToOne(fetch = FetchType.LAZY)
//    val adocao: AdocaoEntity
) {

    constructor(solicitacao: SolicitacaoPetDto) : this(
        nome = solicitacao.nome,
        tipo = solicitacao.tipo,
        adotado = false
    )
}
