package com.bullish.checkout.domain.service

import javax.enterprise.context.ApplicationScoped
import com.bullish.checkout.domain.ports.input.HealthCheckingUseCase

@ApplicationScoped
class HealthCheckingService : HealthCheckingUseCase {
    override fun greet(): String {
        return "Greetings from Quarkus!!"
    }
}