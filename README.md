This is a Vending Machine simulator developed in Java. It comes with an embedded Jetty server and an Api that represents a match to the functionality of real-world vending machine.  

# Install and run with Gradle  
This Application is automatated with the Gradle build tool. It is highly recomended to use this way to deploy it. You can find installation instructions here https://gradle.org/install  

After that running is as simple as:   
gradle build  
gradle run  

# Execute without Gradle  
If you can not install Gradle you can navigate to /build/libs and execute the Jar file of the App with running  
java -jar [jarfile]

# API

The App runs by default at localhost:8002. And the root path is /machine, for example to intialize the machine you need to send a POST request to:  
http://localhost:8002/machine/init  

Endpoints

/init (JSON, empty)  
Initialize the machine  
receives: The machine manager password
returns:  200 if init succeded

/reset (JSON/JSON)  
Reset the machine
receives: The machine manager password
returns:  200 and the collected money if succeded   


/product (JSON/JSON) 
Select a product
receives: The product name {"Product":"Water"}
returns:  The product cost or the product + change if ammount already inserted  

/coin (JSON/JSON) 
Incest coin
receives: The coin value {"Coin":"0.50"}
returns:  

/cancel  
Cancel operation
receives:  
returns:  
