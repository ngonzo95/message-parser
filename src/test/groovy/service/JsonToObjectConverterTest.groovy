package service

import model.Company
import org.junit.Before
import org.junit.Test

class JsonToObjectConverterTest {
    JsonToObjectConverter unit;
    @Before
    def void setup(){
        unit = new JsonToObjectConverter()
    }

    @Test
    def void slurperWorks(){
        Company firstExpectedCompany = new Company(id:1, company: "Hotel California", city:"Santa Barbara", timezone: "US/Pacific")
        Company secondExpectedCompany = new Company(id:2, company: "The Grand Budapest Hotel", city:"Republic of Zubrowka", timezone: "US/Central")

        //TODO fix this path to be relative so that test work on other machines
        List<Company> result = unit.parseCompany('/Users/Nick/projects/message-parser/src/test/data/Companies.json')

        assert firstExpectedCompany == result.get(0)
        assert secondExpectedCompany == result.get(1)

    }
}
