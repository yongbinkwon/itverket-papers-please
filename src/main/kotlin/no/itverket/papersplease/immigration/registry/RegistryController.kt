package no.itverket.papersplease.immigration.registry

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/registry")
class RegistryController(
    private val registryService: RegistryService
) {

    @GetMapping("employee")
    fun employeeExists(
        @RequestParam employeeId: UUID,
        @RequestParam company: String
    ): Boolean = registryService.employeeExists(employeeId, company).also { Thread.sleep(5000) }

    @GetMapping("foreigner")
    fun foreignerExists(
        @RequestParam ssn: UUID,
        @RequestParam nationality: String
    ): Boolean = registryService.foreignerExists(ssn, nationality).also { Thread.sleep(5000) }

    @GetMapping("resident")
    fun residentExists(
        @RequestParam ssn: UUID
    ): Boolean = registryService.residentExists(ssn).also { Thread.sleep(5000) }
}