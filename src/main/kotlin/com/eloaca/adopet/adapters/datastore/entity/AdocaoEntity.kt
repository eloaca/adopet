package com.eloaca.adopet.adapters.datastore.entity

import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "adocoes")
data class AdocaoEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    var dataAdocao: LocalDate,

    @Enumerated(EnumType.STRING)
    val status: StatusAdocao,

    @OneToOne(fetch = FetchType.LAZY)
    val pet: PetEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    val tutor: TutorEntity,
) {

    constructor(pet: PetEntity, tutor: TutorEntity) : this(
        dataAdocao = LocalDate.now(),
        status = StatusAdocao.ADOCAO_EM_ANDAMENTO,
        pet = pet,
        tutor = tutor
    )
}