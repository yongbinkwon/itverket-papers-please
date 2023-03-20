package no.itverket.papersplease.immigration.registry

import org.springframework.data.jpa.repository.JpaRepository

interface ForeignerRegistryRepository: JpaRepository<ForeignerRegistry, Long>