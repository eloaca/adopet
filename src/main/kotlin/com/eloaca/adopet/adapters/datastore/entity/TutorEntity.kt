package com.eloaca.adopet.adapters.datastore.entity

import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import jakarta.persistence.*

@Entity
@Table(name = "tutores")
data class TutorEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val nome: String,

    @Enumerated(EnumType.STRING)
    val tipoDocumento: TipoDocumento,

    val documento: String,

    @OneToMany
    val pets: List<PetEntity>,

    @OneToMany
    val adocoes: List<AdocaoEntity>
) {

}
