package util

import model.Company
import model.Guest
import model.Template

class CommandLineUtils {
    /**
     * Helper method to pick the company
     * @param arg the string that the user passed in
     * @param companies a map of valid companies where they key is the companies id
     * @return the desired company
     */
    static Company pickCompanyFromMap(String arg, Map companies){
        if (arg == ""){
            println("The company id was not passed in. To do so select from this list: ")
            companies.each {Integer k, Company value -> println("$k: $value.company")}
            println("To use the company with id 1 run the gradle command with the argument -PcompanyId=1")
            System.exit(1)
        }
        Integer key = Integer.parseInt(arg)

        if (!companies.containsKey(key)){
            println("The company id provided does not exist please enter one from the list below")
            companies.each {Integer k, Company value -> println("$k: $value.company")}
            System.exit(1)
        }

        companies.get(Integer.parseInt(arg))
    }

    /**
     * Helper method to pick the guest
     * @param arg the string that the user passed in
     * @param guests a map of valid guests where they key is the guest id
     * @return the desired guest
     */
    static Guest pickGuestFromMap(String arg, Map guests){
        if (arg == ""){
            println("The guest id was not passed in. To do so select from this list: ")
            guests.each {Integer k, Guest value -> println("$k: $value.firstName $value.lastName")}
            println("To use the guest with id 1 run the gradle command with the argument -PguestId=1")
            System.exit(1)
        }
        Integer key = Integer.parseInt(arg)

        if (!guests.containsKey(key)){
            println("The guest id provided does not exist please enter one from the list below")
            guests.each {Integer k, Guest value -> println("$k: $value.firstName $value.lastName")}
            System.exit(1)
        }

        guests.get(Integer.parseInt(arg))
    }

    /**
     * Helper method to pick the template
     * @param arg the string that the user passed in
     * @param templates a map of valid templates where they key is the template id
     * @return the desired template
     */
    static Template pickTemplateFromMap(String arg, Map templates){
        if (arg == ""){
            println("The template id was not passed in. To do so select from this list: ")
            templates.each {Integer k, Template value -> println("$k: $value.template")}
            println("To use the template with id 1 run the gradle command with the argument -PtemplateId=1")
            System.exit(1)
        }
        Integer key = Integer.parseInt(arg)

        if (!templates.containsKey(key)){
            println("The template id provided does not exist please enter one from the list below")
            templates.each {Integer k, Template value -> println("$k: $value.template")}
            System.exit(1)
        }

        templates.get(Integer.parseInt(arg))
    }
}
