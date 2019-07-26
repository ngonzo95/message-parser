package service

import groovy.text.SimpleTemplateEngine

class CreateStringFromTemplateService {
    private SimpleTemplateEngine groovyStringGenerator
    CreateStringFromTemplateService(){
        groovyStringGenerator = new SimpleTemplateEngine()
    }

    /**
     * This function create a string leveraging the built in groovy string construct
     * @param template The template for which we want to create a message for
     * @param substitutionMap A map of things that we will use to substitute into the string
     * @return A string with all of the placeholders substituted.
     */
    String createString(String template, Map substitutionMap){
        groovyStringGenerator.createTemplate(template).make(substitutionMap).toString()
    }
}
