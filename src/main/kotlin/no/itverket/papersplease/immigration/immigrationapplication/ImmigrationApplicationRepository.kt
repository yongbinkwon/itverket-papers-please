package no.itverket.papersplease.immigration.immigrationapplication

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ImmigrationApplicationRepository: JpaRepository<ImmigrationApplication, Long> {
    fun findByProcessId(processId: UUID): ImmigrationApplication
}