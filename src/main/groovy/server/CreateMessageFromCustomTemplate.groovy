package server


import service.CreateStringFromTemplateService

import static service.GenerateSubstitutionMap.generateSubstitutionMap

class CreateMessageFromCustomTemplate {
    /**
     * Main method for generation strings from custom templates
     * @param args hotel id, guest id, string of the desired template
     */
    static void main(String[] args){
        Map substitutionMap = generateSubstitutionMap(args[0], args[1])

        String customTemplate = args[2]
        if(customTemplate == ""){
            println("Custom template must not be left out or set to the empty string to set the template use " +
                    "Ptemplate='template string'")
            System.exit(1)
        }

        CreateStringFromTemplateService templateEngine = new CreateStringFromTemplateService()

        try {
            println(templateEngine.createString(customTemplate, substitutionMap))
        } catch(MissingPropertyException e){
            println(e.toString())
            println("The template provided used an unknown property please refer to the cheatsheet in the readme" +
                    "for what properties you can use")
            System.exit(1)
        }
    }

}
