package service

import groovy.json.JsonException
import model.Company
import model.GreetingMessage
import model.Guest

import static constants.FileLocationConstants.*
import static util.CommandLineUtils.pickCompanyFromMap
import static util.CommandLineUtils.pickGuestFromMap

class GenerateSubstitutionMap {

    /**
     *
     * @param companyId string value that the user inputted for the company id
     * @param guestId string value that the user inputted for the guest
     * @return A map to be used for substitutions
     */
    static generateSubstitutionMap(String companyId, String guestId){
        //Load all of the json objects into memory
        Map companies
        Map guests
        Map greetingMessages

        try {
            JsonToObjectConverterService converterService = new JsonToObjectConverterService()
            companies = converterService.parseListOfModelObjects(COMPANIES_PATH, Company)
            guests = converterService.parseListOfModelObjects(GUEST_PATH, Guest)
            greetingMessages = converterService.parseListOfModelObjects(GREETING_MESSAGE_PATH, GreetingMessage)
        } catch(JsonException e){
            println(e.toString())
            println("One of the json objects the parser relies on is improperly formatted please fix this.")
            System.exit(1)
        }

        Company company = pickCompanyFromMap(companyId, companies)

        //prompt the user for the guest id
        Guest guest = pickGuestFromMap(guestId, guests)
        guest.reservation.timezone = company.timezone

        //Get the default greeting message //TODO give the user the ability to select their own greeting
        GreetingMessage greetingMessage = greetingMessages.get(1)
        greetingMessage.timezone = company.timezone

        //Generate the substitutionMap based on these entries
        [company: company, guest: guest, greetingMessage: greetingMessage]

    }
}
