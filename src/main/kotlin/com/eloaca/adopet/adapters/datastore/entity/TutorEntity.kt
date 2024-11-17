package com.eloaca.adopet.adapters.datastore.entity

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import jakarta.persistence.*

@Entity
@Table(name = "tutores")
data class TutorEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nome: String,

    @Enumerated(EnumType.STRING)
    val tipoDocumento: TipoDocumento,

    val documento: String,

) {
    constructor(solicitacao: SolicitacaoTutorDto) : this(
        nome = solicitacao.nome,
        tipoDocumento = solicitacao.tipo,
        documento = solicitacao.documento
    )
}