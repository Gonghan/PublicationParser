PublicationParser
=================

### Write a Java parser to process publication dataset, put them into a database (mysql), and answer some simple queries, e.g., given author A, provide all of her co-authors together with the corresponding publication information. The interface can be a web app.


## 1. How to run it
- JavaEE + MySQL + tomcat 6.0
- Dataset

	You can find the data here: http://dblp.uni-trier.de/xml/
- To be continued.

## 2. Project structure
- Model 

	**CoauthorPath**: contains the path of coauthors. You can find the shortes path(array of persons) of two Persons.
    
	**Person**: contains the basic information of a person and a person map. Here you can get a person object from XML file and get related information like publications about this person.
    
    **Publication**: contains key of the publication.
    
	**DB_helper**: helps to handle the operations on Mysql.    
    


- Service

	to be continued
- UI

	to be continued





