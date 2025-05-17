package com.fiuza.appointment_scheduling.core.usecases.consultation;

import com.fiuza.appointment_scheduling.core.entities.consulation.Consultation;
import com.fiuza.appointment_scheduling.core.exceptions.InternalServerError;
import com.fiuza.appointment_scheduling.core.exceptions.NotFoundException;
import com.fiuza.appointment_scheduling.core.gateway.ConsultationGateway;

public class GetConsultationUseCase {
    private final ConsultationGateway consultationGateway;

    public GetConsultationUseCase(ConsultationGateway consultationGateway) {
        this.consultationGateway = consultationGateway;
    }

    public Consultation execute(Long id) {
        try {
            return consultationGateway.findConsultationById(id)
                    .orElseThrow(() -> new NotFoundException("Consulta não econtrada por favor verifique o id digitado")
                    );

        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e) {
            throw new InternalServerError("Erro interno, por favor tentar novamente");
        }
    }
}
