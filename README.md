PublicationParser
=================

### Write a Java parser to process publication dataset, put them into a database (mysql), and answer some simple queries, e.g., given author A, provide all of her co-authors together with the corresponding publication information. The interface can be a web app.


## 1. How to run it
- JavaEE + MySQL + tomcat 6.0
- Dataset

    You can find the data here: http://dblp.uni-trier.de/xml/

## 2. Project structure
- Model 

	**CoauthorPath**: contains the path of coauthors. You can find the shortes path(array of persons) of two Persons.
    
	**Person**: contains the basic information of a person and a person map. Here you can get a person object from XML file and get related information like publications about this person.
    
    **Publication**: contains key of the publication.
    
	**DB_helper**: helps to handle the operations on Mysql.    
    
    **article**: shows the information about the articles like title, date, key, booktitle. 
    
    **coauthor**: one article has many articles, and several coauthers can link to the same article.


- Service

	**coauthorServlet**: this severlet get two names from users' input and check whether they are valid and then go to authors chain page. Only for GET.
    
    **articlesServlet**: this page can get the articles related to a given author. Only for GET.
- UI

	**index.jsp**: the main entry of the website. Users are required to input two names of the authors. After that, click the submit button and go to the next page.
    
    **error.jsp**: if the inputs from the users are not valid, redirect to this page and guide the user to go back to the index page.
    
    **authors.jsp**: this page shows the chain of the two authors. Also users can view more information about one of the authors by clicking the link.
    
    **articles.jsp**: shows the articles written by a given author.





