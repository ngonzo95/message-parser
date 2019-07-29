package service

import Exception.ParsableException
import groovy.json.JsonException
import model.Company
import model.Guest
import model.Reservation
import org.junit.Before
import org.junit.Test

import java.time.Instant

import static groovy.test.GroovyAssert.shouldFail

class JsonToObjectConverterServiceTest{
    JsonToObjectConverterService unit
    @Before
    void setup(){
        unit = new JsonToObjectConverterService()
    }

   @Test
    void parseCompanyLoadsFromProperlyFormattedJsonCorrectly(){
        Company firstExpectedCompany = new Company(id:1, company: "Hotel California", city:"Santa Barbara", timezone: "US/Pacific")
        Company secondExpectedCompany = new Company(id:2, company: "The Grand Budapest Hotel", city:"Republic of Zubrowka", timezone: "US/Central")

        Map result = unit.parseListOfModelObjects('./src/test/data/Companies.json', Company)

        assert firstExpectedCompany == result.get(1)
        assert secondExpectedCompany == result.get(2)

    }

    @Test
    void parseGuestLoadsFromProperlyFormattedJsonCorrectly(){
        Guest firstExpectedGuest= new Guest(id:1, "firstName": "Candy", "lastName": "Pace",
                "reservation": new Reservation(
                    "roomNumber": 529,
                    "startTimestamp": Instant.ofEpochSecond (1486654792),
                    "endTimestamp": Instant.ofEpochSecond (1486852373)))
        Guest secondExpectedGuest = new Guest("id": 2, "firstName": "Morgan", "lastName": "Porter",
                "reservation": new Reservation(
                    "roomNumber": 385,
                    "startTimestamp": Instant.ofEpochSecond (1486612719),
                    "endTimestamp": Instant.ofEpochSecond (1486694720)))

        Map result = unit.parseListOfModelObjects('./src/test/data/Guests.json', Guest)

        assert firstExpectedGuest == result.get(1)
        assert secondExpectedGuest == result.get(2)

    }

    @Test
    void parseGuestWhenFileHasJsonErrorThrowsJsonException(){
        shouldFail(JsonException) {
            unit.parseListOfModelObjects('./src/test/data/GuestsWithJsonError.json', Guest)
        }
    }

    @Test
    void parseOnSomeObjectThatDoesNotImplementParseThrowException(){
        shouldFail(ParsableException) {
            unit.parseListOfModelObjects('./src/test/data/Guests.json', Reservation)
        }
    }

    @Test
    void parseOnSomeJsonObjectThatDoesNotAlignWithTypeThrowsException(){
        //TODO finish this test case so that it throws a custom exception
//        shouldFail(ParsableException) {
            unit.parseListOfModelObjects('./src/test/data/Guests.json', Company)
//        }
    }
}
