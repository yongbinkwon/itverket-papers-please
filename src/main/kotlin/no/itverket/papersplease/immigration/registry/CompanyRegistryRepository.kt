package no.itverket.papersplease.immigration.registry

import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRegistryRepository: JpaRepository<CompanyRegistry, Long>