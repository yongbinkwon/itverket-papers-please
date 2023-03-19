package no.itverket.papersplease.immigration

import no.itverket.papersplease.immigration.immigrant.ImmigrationDay
import no.itverket.papersplease.immigration.immigrationapplication.ImmigrationApplicationResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("immigrant")
class ImmigrationController(
    private val immigrationOffice: ImmigrationOffice
) {

    @PostMapping
    fun newImmigrant() {
        immigrationOffice.queueImmigrant(ImmigrationApplicationResult.RESIDENT, ImmigrationDay.ZERO)
    }


}