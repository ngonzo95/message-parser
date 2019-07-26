package service

import model.Company
import model.Guest
import model.Reservation
import org.junit.Before
import org.junit.Test

import java.time.Instant

import static groovy.test.GroovyAssert.shouldFail

class CreateStringFromTemplateServiceTest {
    CreateStringFromTemplateService unit

    @Before
    void setup(){
        unit = new CreateStringFromTemplateService()
    }

    @Test
    void whenGivenStringWhichReferencesCompanyAndGuestPrintsProperly(){
        Reservation reservation = new Reservation(roomNumber: 1234, startTimestamp: Instant.now(), endTimestamp: Instant.now())
        Guest guestInput = new Guest(id:4, firstName: "jane", lastName: "doe",reservation: reservation)
        Company companyInput = new Company(id: 23, company: "someName", city: "Seattle")

        //This is added to the test to ensure the evaluation happens in the service
        Guest guest = new Guest(id:6)

        String template = 'Hi $guest.firstName $guest.lastName welcome to $company.company ' +
                'located in $company.city your room number is $guest.reservation.roomNumber'

        String result = unit.createString(template, [company: companyInput, guest: guestInput])

        assert result == 'Hi jane doe welcome to someName located in Seattle your room number is 1234'
    }

    @Test
    void whenGivenStringWhichReferencesSomePropertNotGivenInSubstitutionMapThrowsMissingPropertyException(){
        Reservation reservation = new Reservation(roomNumber: 1234, startTimestamp: Instant.now(), endTimestamp: Instant.now())
        Guest guestInput = new Guest(id:4, firstName: "jane", lastName: "doe",reservation: reservation)
        Company companyInput = new Company(id: 23, company: "someName", city: "Seattle")

        String template = '$someUnkownPlaceHolder'
        shouldFail(MissingPropertyException) {
            unit.createString(template, [company: companyInput, guest: guestInput])
        }
    }

    @Test
    void whenGivenStringWithEscapedDollarSignPrintsProperly(){
        Reservation reservation = new Reservation(roomNumber: 1234, startTimestamp: Instant.now(), endTimestamp: Instant.now())
        Guest guestInput = new Guest(id:4, firstName: "jane", lastName: "doe",reservation: reservation)
        Company companyInput = new Company(id: 23, company: "someName", city: "Seattle")

        String template = 'Hi $guest.firstName we make lots of \\$'

        String result = unit.createString(template, [company: companyInput, guest: guestInput])

        assert result == 'Hi jane we make lots of $'
    }
}
