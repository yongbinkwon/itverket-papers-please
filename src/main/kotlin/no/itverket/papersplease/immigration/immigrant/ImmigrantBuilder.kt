package no.itverket.papersplease.immigration.immigrant

import java.util.*

interface ImmigrantBuilder {

    fun permittedImmigrant(processId: UUID, immigrationDay: ImmigrationDay): Immigrant

    fun nonPermittedImmigrant(processId: UUID, immigrationDay: ImmigrationDay): Immigrant
}