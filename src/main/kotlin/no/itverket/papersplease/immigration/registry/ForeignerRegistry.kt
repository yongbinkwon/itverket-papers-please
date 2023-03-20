package no.itverket.papersplease.immigration.registry

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ForeignerRegistry")
class ForeignerRegistry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "SocialSecurityNumber")
    private val ssn: UUID,

    @Column(name = "Nationality")
    private val nationality: String
): RegistryEntry {
    override fun equals(other: Any?) = this === other || other is ForeignerRegistry && this.equals(other)

    private fun equals(other: ForeignerRegistry) = this.ssn == other.ssn && this.nationality == other.nationality

    override fun hashCode(): Int {
        var result = ssn.hashCode()
        result = 31 * result + nationality.hashCode()
        return result
    }
}