package com.example.usecase

import org.springframework.stereotype.Service

@Service
class HealthCheckUseCase {
    fun execute(): Boolean {
        return true
    }
}