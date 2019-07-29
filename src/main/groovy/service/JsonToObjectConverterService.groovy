package service

import Exception.ParsableException
import groovy.json.JsonSlurper

class JsonToObjectConverterService {
    private JsonSlurper jsonSlurper

    JsonToObjectConverterService(){
        jsonSlurper = new JsonSlurper()
    }

    /**
     * This method converts a json object which is a list of some model object to a map of model objects
     * @param fileNameAndPath the location on the file system where the json object is located
     * @param aClass The type of model object that is in the file
     * @return A map where they key is an id and the value is a specified model type
     */
    Map parseListOfModelObjects(String fileNameAndPath, Class aClass){
        if (aClass.metaClass.getMetaMethod("parse") == null){
            throw new ParsableException(aClass.toString())
        }

        List jsonList = parseJson(fileNameAndPath)
        jsonList.collectEntries(){
        [(it.id) : aClass.parse(it)]
        }
    }

    private List parseJson(String fileNameAndPath){
        File file = new File(fileNameAndPath)
        jsonSlurper.parse(file) as List
    }
}
