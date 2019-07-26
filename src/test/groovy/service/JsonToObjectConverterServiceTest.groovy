package service

import model.Company
import model.Guest
import model.Reservation
import org.junit.Before
import org.junit.Test

import java.time.Instant

class JsonToObjectConverterServiceTest {
    JsonToObjectConverterService unit
    @Before
    void setup(){
        unit = new JsonToObjectConverterService()
    }

    @Test
    void parseCompanyLoadsFromProperlyFormattedJsonCorrectly(){
        Company firstExpectedCompany = new Company(id:1, company: "Hotel California", city:"Santa Barbara", timezone: "US/Pacific")
        Company secondExpectedCompany = new Company(id:2, company: "The Grand Budapest Hotel", city:"Republic of Zubrowka", timezone: "US/Central")

        List<Company> result = unit.parseCompanies('./src/test/data/Companies.json')

        assert firstExpectedCompany == result.get(0)
        assert secondExpectedCompany == result.get(1)

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

        List<Guest> result = unit.parseGuests('./src/test/data/Guests.json')

        assert firstExpectedGuest == result.get(0)
        assert secondExpectedGuest == result.get(1)

    }
}
