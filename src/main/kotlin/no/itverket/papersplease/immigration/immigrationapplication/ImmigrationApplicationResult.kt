package no.itverket.papersplease.immigration.immigrationapplication

enum class ImmigrationApplicationResult(val permitted: Boolean) {
    RESIDENT(true),
    UNREGISTERED_RESIDENT(false),
    FOREIGNER(true),
    UNREGISTERED_FOREIGNER(false),
    INVALID_FOREIGN_NATION(false),
    INVALID_PASSPORT_IMAGE(false),
    VISA_IMMIGRANT(true),
    UNREGISTERED_VISA_IMMIGRANT(false),
    INVALID_EMPLOYER(false),
    INVALID_VISA_IMAGE(false)
}