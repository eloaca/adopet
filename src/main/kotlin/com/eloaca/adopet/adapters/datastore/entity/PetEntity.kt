package com.eloaca.adopet.adapters.datastore.entity

import jakarta.persistence.*

@Entity
@Table(name = "pets")
data class PetEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val nome: String,

    @OneToOne(fetch = FetchType.LAZY)
    val tutor: TutorEntity,

    @OneToOne(fetch = FetchType.LAZY)
    val adocao: AdocaoEntity
)
