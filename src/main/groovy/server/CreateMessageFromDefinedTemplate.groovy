package server


import groovy.json.JsonException
import model.Template
import service.CreateStringFromTemplateService
import service.JsonToObjectConverterService
import util.CommandLineUtils

import static constants.FileLocationConstants.TEMPLATE_PATH
import static service.GenerateSubstitutionMap.generateSubstitutionMap

class CreateMessageFromDefinedTemplate {
    /**
     * Main method for generation strings from predefined templates
     * @param args
     */
    static void main(String[] args){
        Map substitutionMap = generateSubstitutionMap(args[0], args[1])

        Map templates
        try {
            JsonToObjectConverterService converterService = new JsonToObjectConverterService()
            templates = converterService.parseListOfModelObjects(TEMPLATE_PATH, Template)
        } catch(JsonException e) {
            println(e.toString())
            println("The template's json object is improperly formatted please fix this.")
            System.exit(1)
        }

        Template template = CommandLineUtils.pickTemplateFromMap(args[2], templates)

        CreateStringFromTemplateService templateEngine = new CreateStringFromTemplateService()

        println(templateEngine.createString(template.template, substitutionMap))
    }

}
