package com.eloaca.adopet.core.ports.datastore

import com.eloaca.adopet.adapters.datastore.entity.TutorEntity
import com.eloaca.adopet.adapters.domain.enums.TipoDocumento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TutorRepository : JpaRepository<TutorEntity, Long> {

    fun existsByTipoDocumentoAndDocumento(tipo: TipoDocumento, documento: String) : Boolean
}