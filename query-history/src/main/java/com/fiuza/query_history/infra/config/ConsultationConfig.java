package com.fiuza.query_history.infra.config;

import com.fiuza.query_history.core.gateway.ConsultationGateway;
import com.fiuza.query_history.core.usecases.GetConsultationUseCase;
import com.fiuza.query_history.infra.adapter.ConsultationRepositoryImp;
import com.fiuza.query_history.infra.repository.ConsultationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultationConfig {

    @Bean
    public ConsultationGateway consultationGateway(ConsultationRepository consultationRepository) {
        return new ConsultationRepositoryImp(consultationRepository);
    }

    @Bean
    public GetConsultationUseCase getConsultationUseCase(
            ConsultationGateway consultationGateway
    ) {
        return new GetConsultationUseCase(consultationGateway);
    }
}
