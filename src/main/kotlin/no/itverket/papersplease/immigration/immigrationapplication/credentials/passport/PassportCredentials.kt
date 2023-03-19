package no.itverket.papersplease.immigration.immigrationapplication.credentials.passport

import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import no.itverket.papersplease.immigration.immigrationapplication.credentials.Credentials

@Embeddable
class PassportCredentials(
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "Fk__Passport__ImmigrationProcess")
    private val passport: Passport
): Credentials {
    override fun equals(other: Any?) = this === other || other is PassportCredentials && this.passport == other.passport

    override fun hashCode() = passport.hashCode()
}