package com.example.semesterprojekt.models

object Validator {

    fun validateGameTitle(title: String): ValidationResult {
        return ValidationResult(title.isNotBlank())
    }

    fun validateGameYear(year: String): ValidationResult {
        return ValidationResult(year.isNotBlank())
    }

    fun validateGamePublisher(publisher: String): ValidationResult {
        return ValidationResult(publisher.isNotBlank())
    }

    fun validateGameDeveloper(developer: String): ValidationResult {
        return ValidationResult(developer.isNotBlank())
    }

    fun validateGamePlatform(platform: List<Platform>): ValidationResult {
        return ValidationResult(platform.isNotEmpty())
    }

    fun validateGameImage(image: String): ValidationResult {
        return ValidationResult(image.isNotBlank())
    }

    fun validateGameRating(rating: String): ValidationResult {
        return ValidationResult(rating.isNotBlank())
    }

    fun validateHours(hours: String): Boolean {
        return hours.isNotBlank() && hours.toDoubleOrNull() != null && hours.toDouble()
            .let { it in 0.0..9999.9 }
    }

}

data class ValidationResult(
    val successful: Boolean
)