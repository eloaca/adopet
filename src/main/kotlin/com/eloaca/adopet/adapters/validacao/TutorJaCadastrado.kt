package com.eloaca.adopet.adapters.validacao

import com.eloaca.adopet.adapters.controller.dto.SolicitacaoTutorDto
import com.eloaca.adopet.core.ports.datastore.TutorRepository
import com.eloaca.adopet.core.ports.validacao.TutorValidacao
import org.springframework.stereotype.Component

@Component
class TutorJaCadastrado(val repository: TutorRepository) : TutorValidacao {
    override fun validar(solicitacao: SolicitacaoTutorDto) {
        val tutorCadastrado = repository.existsByTipoDocumentoAndDocumento(solicitacao.tipo, solicitacao.documento)
        if (tutorCadastrado) {
            throw RuntimeException()
        }
    }
}