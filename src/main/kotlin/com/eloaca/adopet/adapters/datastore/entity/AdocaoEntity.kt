package com.eloaca.adopet.adapters.datastore.entity

import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "adocoes")
data class AdocaoEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var dataHoraSolicitacao: LocalDateTime,

    var dataHoraAdocao: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: StatusAdocao,

    @OneToOne(fetch = FetchType.LAZY)
    val pet: PetEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    val tutor: TutorEntity,
) {

    constructor(pet: PetEntity, tutor: TutorEntity, status: StatusAdocao) : this(
        dataHoraSolicitacao = LocalDateTime.now(),
        status = status,
        pet = pet,
        tutor = tutor
    )
}