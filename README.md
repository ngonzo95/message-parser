# message-parser
This is a template engine that could be used to generate preset messages to a guest of some hotel. Messages can be made and reused by adding them to a template file and guests and hotels can be added or removed as well. This project was made to satisfy a coding challenge and was done in approximately 10 hours.
## Getting Started
This project uses gradle to make running the project easy on any computer. All you need to run the gradle wrapper
is java and it will automatically install the required packages. The java version I am using on my machine is
java 12.0.2 but any recent version of java should work. To check your java version run ``java --version``. If you
do not have java on your machine install it using your favorite package manager or follow the instructions 
[here](https://docs.oracle.com/en/java/javase/12/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

Once you have java all you need to do is go to the project directory and run ``./gradlew --version`` if
using Linux/Mac or ``gradlew.bat --version`` if using Windows. you should get output that looks like
```
Gradle 5.2.1

Build time:   2019-02-08 19:00:10 UTC
Revision:     f02764e074c32ee8851a4e1877dd1fea8ffb7183

Kotlin DSL:   1.1.3
Kotlin:       1.3.20
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.13 compiled on July 10 2018
JVM:          12.0.2 (Oracle Corporation 12.0.2+10)
OS:           Mac OS X 10.13.6 x86_64
```
If you do not get output that looks like this it is most likely an issue with your java. Try upgrading it to at least
12.0.2
For the rest of the readme I will use the only ``./gradlew`` command but know that if you are using windows you
should replace ``./gradlew`` with ``gradlew.bat``

## Groovy and Gradle Warning Message Disclaimer
After working on this project for a while I realised that every time a groovy task is run in gradle you get
the warning message:
```
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass (file:/Users/Nick/.gradle/caches/modules-2/files-2.1/org.codehaus.groovy/groovy-all/2.4.15/423a17aeb2f64bc6f76e8e44265a548bec80fd42/groovy-all-2.4.15.jar) to method java.lang.Object.finalize()
WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```
This is a problem with groovy and newer versions of java. There seems to be some hacks to fix this warning message
but I did not have enough time to implement them and hoped that this would be acceptable in a sample project. 
One easy work around was to use the groovy console instead of gradle but I wanted to make this project as easy to 
install as possible so I decided to continue to use gradle.

## Running The Template Engine
To run the the template engine with an existing template run the following command 
```
./gradlew runBasedOnExistingTemplate -PcompanyId=3 -PguestId=5 -PtemplateId=2
```
Which returns
```
Good afternoon Latoya, and welcome to The Heartbreak Hotel! Room 194 is now ready you. Enjoy your stay, and let us know if you need anything.
```
This will generate a string based on a template, guest and company whose ids were provided. To see what those id
map to you can either look at the respective json files or you can omit one of the arguments and the program
will provide you with a list of options for that argument.
For example if you run
```
./gradlew runBasedOnExistingTemplate -PguestId=5 -PtemplateId=2
```
you will get something that looks like
```
The company id was not passed in. To do so select from this list: 
1: Hotel California
2: The Grand Budapest Hotel
3: The Heartbreak Hotel
4: The Prancing Pony
5: The Fawlty Towers
To use the company with id 1 run the gradle command with the argument -PcompanyId=1

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':runBasedOnExistingTemplate'.
> Process 'command '/Library/Java/JavaVirtualMachines/jdk-12.0.2.jdk/Contents/Home/bin/java'' finished with non-zero exit value 1
```

To run with a custom template use like the following comman.
```
./gradlew runBasedOnCustomTemplate -PguestId=5 -PcompanyId=4 -Ptemplate='$greetingMessage $guest.firstName, this is a custom template'
```
which returns
```
Good afternoon Latoya, this is a custom template
```
Note that the Ptemplate argument must use single quotes and not double quotes

## Defining Templates
The template structure is based on the built in groovy string. In groovy strings the $ sign tells groovy 
to execution this code, convert the result to a string and add the result to that location in the string.
For example the template ``Hi $guest.firstName`` retrieves the firstName property from the guest object
and then creates a string that starts with 'Hi ' and ends with the result.

Below is a cheatsheet to see what parameters you can use when defining your custom template where bullets that 
have a : represents a property that you can use.
* guest
    - id        : id of the user
    - firstName : guest's first name
    - lastName  : guest's last name
    - reservation
        * roomNumber : guest's room number
        * startTime  : formatted check in time for guest
        * endTime    : formatted checkout time for guest
* company
    - id       : id of the company
    - company  : name of the company
    - city     : city where the company is located
    - timezone : the timezone where the company is 
                 located
* greetingMessage : the greeting message 
                    based on time of day
    - morningMessage : the morning greeting message
    - afternoonMessage : the afternoon greeting message
    - eveningMessage : the evening greeting message

In actuality there are more properties that you can use but they are not explicit supported to map 
to a string that the user want to see so do so at your own risk.

On top of these basic properties groovy also supports execution of more complex code using ${}
so for example if you wanted to get the room number of the room next to some guests you can use
```${guest.reservation.roomNumber + 1}```
I recommend looking at some of the provided templates to get a feel of how the templating works

## Design Decisions 
When designing the template engine I wanted to develop it in a way that was easily extensible in the future.
Furthermore I wanted it to be robust, testable and easy to install. To make it extensible I based the template on 
the groovy string which means that the business logic for rendering strings for various properties is
separated from logic of the templating engine. This means that the logic for adding support for a new substation
model will be mostly self contained. Furthermore by using the groovy string as a templating engine
we can trust its robustness since it is a heavily used construct. I also developed most of it using TDD.
In order to ensure it was easy to test I made small methods which were only handling one thing.
To make it easy to install I used gradle which only requires having an update to date version of
java installed on your machine. I decided to make the user interface a CLI because it 
was easier to support a CLI in gradle then it was to prompt the user.


## Why Groovy
By this point I am sure you can guess that the programming language I picked. The main reason I
decided to use groovy was the support of groovy strings. Groovy strings are a powerful construct 
that is built for problems exactly like this one and is natively supported in groovy. This allowed
me to focus on the business logic of what the various fields represent and what transformation are
required to make them print correctly. On top of this groovy also has support for defining properties
using only getters. This made witting the logic in the Reservation model to transform startTimestamp
into startTime easy to implement and easy to use. In the future I believe this construct will make 
building properties for other models easy as well.

## Test Cases
To verify the correctness of this project I created test suite which tests all of the functionality 
outside of the script which collects user input. To ensure the correctness of the script which collects
and applies user input I manual tested the various cases. If I had more time I would write test cases 
for this as well. To run the test suit use the command ``./gradlew test`` Once this successfully runs you
can view the results by opening ``build/reports/tests/test/index.html``. Also note that the test cases 
depend on data in ./src/test/data being unchanged. So while you a free to change the data in ./data 
which is used when running the groovy scripts don't change anything in ./src/test/data

## Future work
With more time there is alot that I would do. Throughout the code I have added todos for different 
things I would like to add. In a nutshell the things I would like to do most are:
1. Build a GUI user interface to make selecting and building templates easier.
2. Find a fix or find a way to hide or the ``WARNING: An illegal reflective access operation has occurred``
   message that display whenever groovy compiles. 
3. Improve the error handling so that the user gets error messages that are more human readable. Currently
   I still allow exceptions to be thrown because it provides useful information about where and why the 
   failure is occurring.
4. Add more objects that the user can inject into messages.
5. Add the ability to create more than one message each time it runs.
6. Do a deeper dive into the security concerns of using groovy strings which can execute arbitrary code.
   This concern was mitigated by only allowing the groovy string engine access to certain objects.
