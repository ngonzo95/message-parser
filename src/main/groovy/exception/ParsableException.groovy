package exception

class ParsableException extends RuntimeException {
    ParsableException(String modelName){
        super("The model $modelName does not implment the parsable trait")
    }
}
