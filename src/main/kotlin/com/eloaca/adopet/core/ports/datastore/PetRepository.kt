package com.eloaca.adopet.core.ports.datastore

import com.eloaca.adopet.adapters.datastore.entity.PetEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<PetEntity, Long> {
}