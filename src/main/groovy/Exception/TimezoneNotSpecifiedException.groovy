package Exception

class TimezoneNotSpecifiedException extends RuntimeException {
    TimezoneNotSpecifiedException(){
        super("The Object you are trying to use requires timezone be specified before you access this parameter")
    }
}
