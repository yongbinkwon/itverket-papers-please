package no.itverket.papersplease.immigration

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
        immigrationOffice.queueImmigrant()
    }


}