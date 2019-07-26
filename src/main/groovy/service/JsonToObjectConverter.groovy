package service

import groovy.json.JsonSlurper
import model.Company

class JsonToObjectConverter {
    private JsonSlurper jsonSlurper;

    def JsonToObjectConverter (){
        jsonSlurper = new JsonSlurper();
    }

    public List<Company> parseCompany(String fileNameAndPath){
        File file = new File(fileNameAndPath)
        List jsonList = jsonSlurper.parse(file) as List

        jsonList.collect{new Company(id: it.id, company: it.company, city: it.city, timezone: it.timezone)}
    }
}
