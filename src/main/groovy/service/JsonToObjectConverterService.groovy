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

    List<Company> parseCompanies(String fileNameAndPath){
        List jsonList = parseJson(fileNameAndPath)
        jsonList.collect{new Company(id: it.id, company: it.company, city: it.city, timezone: it.timezone)}
    }

    List<Guest> parseGuests(String fileNameAndPath){

        List jsonList = parseJson(fileNameAndPath)
        jsonList.collect{
            Reservation reservation = new Reservation(roomNumber: it.reservation.roomNumber,
                    startTimestamp: Instant.ofEpochSecond(it.reservation.startTimestamp as long),
                    endTimestamp: Instant.ofEpochSecond(it.reservation.endTimestamp as long))
            new Guest(id: it.id, firstName: it.firstName, lastName: it.lastName, reservation: reservation)
        }
    }

    private List parseJson(String fileNameAndPath){
        File file = new File(fileNameAndPath)
        jsonSlurper.parse(file) as List
    }
}
