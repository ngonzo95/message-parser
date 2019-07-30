package model

import Enums.TimeOfDayEnumeration
import util.DateUtil

import java.time.Instant

import static Enums.TimeOfDayEnumeration.AFTERNOON
import static Enums.TimeOfDayEnumeration.MORNING

class GreetingMessage {
    String morningMessage
    String afternoonMessage
    String eveningMessage
    String timezone


    @Override
    String toString() {
        TimeOfDayEnumeration timeOfDay = DateUtil.getTimeOfDayEnum(Instant.now(), timezone)

        if (timeOfDay == MORNING){
            return morningMessage
        } else if(timeOfDay == AFTERNOON){
            return afternoonMessage
        } else {
            return eveningMessage
        }
    }
}
