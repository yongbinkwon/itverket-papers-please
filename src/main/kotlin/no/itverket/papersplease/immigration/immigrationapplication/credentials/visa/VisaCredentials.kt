package no.itverket.papersplease.immigration.immigrationapplication.credentials.visa

import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import no.itverket.papersplease.immigration.immigrationapplication.credentials.Credentials

@Embeddable
class VisaCredentials(
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "Fk__Visa__ImmigrationProcess")
    private val visa: Visa
): Credentials {
    override fun equals(other: Any?) = this === other || other is VisaCredentials && this.visa == other.visa

    override fun hashCode() = visa.hashCode()
}