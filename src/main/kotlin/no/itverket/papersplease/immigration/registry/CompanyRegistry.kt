package no.itverket.papersplease.immigration.registry

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "CompanyRegistry")
class CompanyRegistry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "EmployeeId")
    private val employeeId: UUID,

    @Column(name = "Company")
    private val company: String
): RegistryEntry {
    override fun equals(other: Any?) = this === other || other is CompanyRegistry && this.equals(other)

    private fun equals(other: CompanyRegistry) = this.employeeId == other.employeeId && this.company == other.company

    override fun hashCode(): Int {
        var result = employeeId.hashCode()
        result = 31 * result + company.hashCode()
        return result
    }
}