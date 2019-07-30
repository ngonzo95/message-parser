package model

import Exception.TimezoneNotSpecifiedException
import org.junit.Test
import spock.lang.Specification
import util.DateUtil

import java.time.Instant

import static Enums.TimeOfDayEnumeration.*

/**
 * Because this test required mocking of static methods a more robust testing framework was needed.
 * In this case I used spock to mock out static methods
 */
class GreetingMessageTest extends Specification {

    @Test
    void whenGreetingMessageConvertedToStringGivesMorningMessageInMorning(){
        given:
        GroovyMock(DateUtil, global: true)
        GroovyMock(Instant, global: true)

        when:
        GreetingMessage greetingMessage = new GreetingMessage(morningMessage: "morning",
                afternoonMessage: "afternoon", eveningMessage: "evening", timezone: "US/Pacific")
        String result = "$greetingMessage"

        then:
        interaction {
            Instant mockInstant = Instant.ofEpochSecond(1560604541)
            1 * Instant.now() >> mockInstant
            1 * DateUtil.getTimeOfDayEnum(mockInstant, "US/Pacific") >> MORNING
        }
        assert result == greetingMessage.morningMessage
    }

    @Test
    void whenGreetingMessageConvertedToStringGivesAfternoonMessageInAfternoon() {
        given:
        GroovyMock(DateUtil, global: true)
        GroovyMock(Instant, global: true)

        when:
        GreetingMessage greetingMessage = new GreetingMessage(morningMessage: "morning",
                afternoonMessage: "afternoon", eveningMessage: "evening", timezone: "US/Pacific")
        String result = "$greetingMessage"

        then:
        interaction {
            Instant mockInstant = Instant.ofEpochSecond(1560604541)
            1 * Instant.now() >> mockInstant
            1 * DateUtil.getTimeOfDayEnum(mockInstant, "US/Pacific") >> AFTERNOON
        }
        assert result == greetingMessage.afternoonMessage
    }

    @Test
    void whenGreetingMessageConvertedToStringGivesEveningMessageInEvening() {
        given:
        GroovyMock(DateUtil, global: true)
        GroovyMock(Instant, global: true)

        when:
        GreetingMessage greetingMessage = new GreetingMessage(morningMessage: "morning",
                afternoonMessage: "afternoon", eveningMessage: "evening", timezone: "US/Pacific")
        String result = "$greetingMessage"

        then:
        interaction {
            Instant mockInstant = Instant.ofEpochSecond(1560604541)
            1 * Instant.now() >> mockInstant
            1 * DateUtil.getTimeOfDayEnum(mockInstant, "US/Pacific") >> EVENING
        }
        assert result == greetingMessage.eveningMessage
    }

    @Test
    void whenGreetingMessageDoesNotHaveTimezoneSpecifiedThrowTimezoneNotSpecifiedException() {
        given:
        GroovyMock(DateUtil, global: true)
        GroovyMock(Instant, global: true)

        when:
        GreetingMessage greetingMessage = new GreetingMessage(morningMessage: "morning",
                afternoonMessage: "afternoon", eveningMessage: "evening")

        String result = "$greetingMessage"

        then:
        TimezoneNotSpecifiedException ex = thrown()
    }
}