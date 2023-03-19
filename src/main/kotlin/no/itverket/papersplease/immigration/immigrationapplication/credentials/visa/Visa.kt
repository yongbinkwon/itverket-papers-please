package no.itverket.papersplease.immigration.immigrationapplication.credentials.visa

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Visa")
class Visa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "Employer")
    private val employer: String = "NOT_IN_USE",

    @Column(name = "EmployeeName")
    private val employeeName: String = "NOT_IN_USE",

    @Column(name = "EmployeeId")
    private val employeeId: String = "NOT_IN_USE",

    @Column(name = "Image")
    private val image: UUID,
) {
    override fun equals(other: Any?) = this === other || other is Visa && this.equals(other)

    private fun equals(other: Visa) = this.employer == other.employer &&
            this.employeeName == other.employeeName &&
            this.image == other.image

    override fun hashCode(): Int {
        var result = employer.hashCode()
        result = 31 * result + employeeName.hashCode()
        result = 31 * result + employeeId.hashCode()
        result = 31 * result + image.hashCode()
        return result
    }
}