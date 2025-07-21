package com.example.usecase

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class HealthCheckUseCaseTest {

    @Test
    fun `health check returns true`() {
        val useCase = HealthCheckUseCase()
        val result = useCase.execute()
        assertTrue(result)
    }
}