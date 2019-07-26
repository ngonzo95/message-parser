package service

import groovy.json.JsonSlurper
import model.Company
import model.Guest
import model.Reservation

import java.time.Instant

class JsonToObjectConverterService {
    private JsonSlurper jsonSlurper

    JsonToObjectConverterService(){
        jsonSlurper = new JsonSlurper()
    }

    /**
     * This method converts a json object which is a list of companies to a map of companies
     * @param fileNameAndPath the location on the file system where the json object is located
     * @return A map where they key is an id and the value is a company
     */
    Map<Long, Company> parseCompanies(String fileNameAndPath){
        List jsonList = parseJson(fileNameAndPath)
        jsonList.collectEntries(){
            Company company = new Company(id: it.id, company: it.company,
                city: it.city, timezone: it.timezone)
        [(it.id) : company]
        }
    }

    /**
     * This method converts a json object which is a list of guests to a map of guests
     * @param fileNameAndPath the location on the file system where the json object is located
     * @return A map where they key is an id and the value is a guest
     */
    Map<Long, Guest> parseGuests(String fileNameAndPath){

        List jsonList = parseJson(fileNameAndPath)
        jsonList.collectEntries(){
            Reservation reservation = new Reservation(roomNumber: it.reservation.roomNumber,
                    startTimestamp: Instant.ofEpochSecond(it.reservation.startTimestamp as long),
                    endTimestamp: Instant.ofEpochSecond(it.reservation.endTimestamp as long))
            Guest guest = new Guest(id: it.id, firstName: it.firstName, lastName: it.lastName, reservation: reservation)
            [(it.id) : guest]
        }
    }

    private List parseJson(String fileNameAndPath){
        File file = new File(fileNameAndPath)
        jsonSlurper.parse(file) as List
    }
}
