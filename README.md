# Spring Learning Demo

This is only a spring demo for learning purposes.   
The main goal of the project was understood the SpringBoot basics.  

It includes the following:    

-JPA example for Postgres  
-Hibernate annotations   
-Spring annotations like @Component @Service @RestController @RequestMapping @ResponseBody @GetMapping  
-Maven POM   
-Migration using flyway and SQL files  
-Application Properties  
-Log4 Properties  
-Messages.Properties  
-Pageable example  
-Simple error handling  
-Junit  

The Main function is a basic approach to persists Website's info and a simple Rating system.  

The Website Model is:

Name   
URL  
Rating  
Country  
Technologies  

You can create, get, update  and delete Websites  
You can create, get, update  and delete Country's  
You can create, get, update  and delete technologies  
You can create, get, update  and delete ratings  

A website can only have one Country. 
A website can have many Technologies.
The website rating is calculated when every new rating was added to a specific website using a simple average formula rounded to the nearest int.


