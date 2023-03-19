package no.itverket.papersplease.immigration.immigrationapplication

enum class ImmigrationApplicationResult(val permitted: Boolean) {
    MATCHING_PASSPORT_IMAGE(true),
    DIFFERENT_PASSPORT_IMAGE(false),
    MATCHING_VISA_IMAGE(true),
    DIFFERENT_VISA_IMAGE(false),
    RESIDENT(true)
}