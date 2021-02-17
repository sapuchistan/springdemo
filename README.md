# Spring Learning Demo

This is only a spring demo for learning purposes.   
The main goal of the aplication was understand the SpringBoot basics.  

It includes the following:    

-JPA example for Postgres  
-Hibernate annotations   
-Spring annotations like @Component @Service @RestController @RequestMappiong @ResponseBody @GetMapping  
-Maven POM   
-Migration using flyway and SQL files  
-Application Properties  
-Log4 Properties  
-Messages.Properties  
-Pageable example  
-Simple error handling  
-Junit  

The Main function is a basic approach to persists Website's info and a simple Rating system.  

The Web site Model is:

Name   
URL  
Rating  
Country  
Technologies  

You can create, get, update  and delete Websites  
You can create, get, update  and delete Countrys  
You can create, get, update  and delete technologies  
You can create, get, update  and delete ratings  

A web site can only have one Country. 
A web site can have many Technologies.
The website rating is calculate when every new rating was added to the web site using a simple average formula rounding to the next nearest int.


