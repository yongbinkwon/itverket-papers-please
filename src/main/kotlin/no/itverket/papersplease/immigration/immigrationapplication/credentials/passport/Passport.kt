package no.itverket.papersplease.immigration.immigrationapplication.credentials.passport

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Passport")
class Passport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "Nationality")
    private val nationality: String = "NOT_IN_USE",

    @Column(name = "SocialSecurityNumber")
    private val ssn: UUID = UUID.randomUUID(),

    @Column(name = "Image")
    private val image: UUID
) {
    override fun equals(other: Any?) = this === other || other is Passport && this.equals(other)

    private fun equals(other: Passport) = this.nationality == other.nationality &&
            this.ssn == other.ssn &&
            this.image == other.image

    override fun hashCode(): Int {
        var result = nationality.hashCode()
        result = 31 * result + ssn.hashCode()
        result = 31 * result + image.hashCode()
        return result
    }
}