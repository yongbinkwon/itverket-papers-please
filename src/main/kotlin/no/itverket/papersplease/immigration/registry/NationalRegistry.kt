package no.itverket.papersplease.immigration.registry

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "NationalRegistry")
class NationalRegistry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "SocialSecurityNumber")
    private val ssn: UUID,
): RegistryEntry {
    override fun equals(other: Any?) = this === other || other is NationalRegistry && this.ssn == other.ssn

    override fun hashCode() = ssn.hashCode()
}