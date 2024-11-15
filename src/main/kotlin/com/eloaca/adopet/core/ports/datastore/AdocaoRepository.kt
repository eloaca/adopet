package com.eloaca.adopet.core.ports.datastore

import com.eloaca.adopet.adapters.datastore.entity.AdocaoEntity
import com.eloaca.adopet.adapters.domain.enums.StatusAdocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdocaoRepository : JpaRepository<AdocaoEntity, Long> {

    fun countByTutorId(idTutor: Long) : Int

    fun existsByPetIdAndStatus(idPet: Long, status: StatusAdocao) : Boolean

    fun findByTutorId(idTutor: Long) : List<AdocaoEntity>

    fun findByStatus(status: StatusAdocao) : List<AdocaoEntity>
}