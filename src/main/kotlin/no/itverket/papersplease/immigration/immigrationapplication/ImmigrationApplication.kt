package no.itverket.papersplease.immigration.immigrationapplication

import jakarta.persistence.*
import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.credentials.Credentials
import java.util.*

@Entity
@Table(name = "ImmigrationApplication")
class ImmigrationApplication(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private val id: Long = 0,

    @Column(name = "ProcessId")
    private val processId: UUID,

    @Column(name = "ExpectedResult")
    private val expectedResult: ImmigrationApplicationResult,

    @Column(name = "Face")
    private val face: UUID,

    @Column(name = "ImmigrationDay")
    private val immigrationDay: ImmigrationDay,

    @Embedded
    private val credentials: Credentials
) {

    override fun equals(other: Any?) = this === other || other is ImmigrationApplication && this.equals(other)

    private fun equals(other: ImmigrationApplication) = this.processId == other.processId &&
            this.expectedResult == other.expectedResult &&
            this.face == other.face &&
            this.immigrationDay == other.immigrationDay &&
            this.credentials == other.credentials

    override fun hashCode(): Int {
        var result = processId.hashCode()
        result = 31 * result + expectedResult.hashCode()
        result = 31 * result + face.hashCode()
        result = 31 * result + immigrationDay.hashCode()
        result = 31 * result + credentials.hashCode()
        return result
    }

}