package model

import enums.TimeOfDayEnumeration
import exception.TimezoneNotSpecifiedException
import groovy.transform.EqualsAndHashCode
import traits.Parseable
import util.DateUtil

import java.time.Instant

import static enums.TimeOfDayEnumeration.AFTERNOON
import static enums.TimeOfDayEnumeration.MORNING

@EqualsAndHashCode(includeFields=true)
class GreetingMessage implements Parseable{
    String morningMessage
    String afternoonMessage
    String eveningMessage
    String timezone
    int id


    /**
     * This method overrides the default string operation for greeting message so that it returns the proper
     * greeting message based on the time of day. if no timezone is specified an exception will be thrown
     * TODO rethink of the approach of overriding the toString method as this makes printing out failed tests difficult
     * @return A greeting message based on the time of day
     */
    @Override
    String toString() {

        if(timezone == null){
            throw new TimezoneNotSpecifiedException()
        }

        TimeOfDayEnumeration timeOfDay = DateUtil.getTimeOfDayEnum(Instant.now(), timezone)

        if (timeOfDay == MORNING){
            return morningMessage
        } else if(timeOfDay == AFTERNOON){
            return afternoonMessage
        } else {
            return eveningMessage
        }
    }

    static GreetingMessage parse(Map map){
        return new GreetingMessage(id: map.id, morningMessage: map.morningMessage,
                afternoonMessage: map.afternoonMessage, eveningMessage: map.eveningMessage)
    }
}
