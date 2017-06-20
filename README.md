#Everest
Everest is a HATEOAS compliant RESTful web service that exposes custom workplace metrics and data. The project was born as an opportunity for employees to learn more about specific development technologies, as well as to address a server-side need for a few internal applications.  Read more about the Everest Project in the [About section](#about) below.  

##Build Statuses
[![Build Status](https://travis-ci.org/xpanxion/everest.svg)](https://travis-ci.org/xpanxion/everest) [![Coverage Status](https://coveralls.io/repos/xpanxion/everest/badge.svg?branch=master&service=github)](https://coveralls.io/github/xpanxion/everest?branch=master) master

##Install
**1) Setup**  
Assuming that MySQL server has been installed, first create a database schema using the following.  This only needs to be done once.  
```mysql
CREATE DATABASE everest;
```

### Build the Database
After the Database is created, we need to configure how the database will be built. How you do this is up to your own personal development preference. A couple of suggested ways are:  

**a) Rebuild on Application Start**  
Edit the file **src/main/resources/application.properties**. We need to update the field *spring.jpa.hibernate.ddl-auto* to **create**:  
```
# Hibernate ddl auto (create, create-drop, update): with "update" the database
# schema will be automatically updated accordingly to java entities found in
# the project
spring.jpa.hibernate.ddl-auto=create
```
  
**b) Build Manually**  
Open a connection to MySQL and run the file **src/main/resources/import.sql**. E.g.:  

```
mysql -u root -p < src/main/resources/import.sql
```

**2) Build**  
Build the project using maven:  
```bash
cd PROJECT_DIRECTORY
mvn install
```  
This will build the project's **everest.jar** file to PROJECT_DIRECTORY/target/everest.jar.  

**3) Deploy**  
There are a few ways to deploy the application locally.  

3a) Command Line via Maven/Spring Tools  
This will run the project by deploying the project contents using the in-project defined servlet container.
```bash
mvn spring-boot:run
```

3b) Command Line via full build  
Assuming ```mvn clean install``` has been excuted, this will run the PROJECT_DIRECTORY/target/everest.jar using the in-project defined servlet container.
```bash
java -jar target/everest.jar
```

3c) IDE  
If you prefer, you may run the application directly out of your IDE by running **Application.java** located in the src/main/java directory. This is possible thanks to Spring Boot.  

**4) Authentication**  
Before we can use the application, we must add our authentication credentials. The application currently requires simple token based authentication in the form of an HTTP header. If you are using a web browser, use your favorite plugin (we suggest Modify Headers: [For Chrome](https://chrome.google.com/webstore/detail/modify-headers-for-google/innpjfdalfhpcoinfnehdnbkglpmogdi?hl=en-US), [For Firefox](https://addons.mozilla.org/en-US/firefox/addon/modify-headers/)).  

Set the following header:  

```
X-AUTH-TOKEN: 5d89az-x8a7q264-115z9fpq-91acq4
```

**5) Test**  
At this point the application should be up and running at http://127.0.0.1:8080/api. Assuming you have completed everything correctly, you should see a response similar to the following: 

```json
{
  "_links" : {
    "data" : {
      "href" : "http://127.0.0.1:8080/api/data"
    },
    "service" : {
      "href" : "http://127.0.0.1:8080/api/service"
    }
  }
}
```

Additional information regarding the install process can be found in the [Deploying Everest](docs/deploying-everest.md) document.

##Usage
Everest seeks to be HATEOAS compliant, which means that it follows a "self-documenting" structure.  For example, see the following GET request to **http://localhost:8080/api/data**  

```json
{
  "_links" : {
    "locales" : {
      "href" : "http://localhost:8080/api/data/locales"
    },
    "employees" : {
      "href" : "http://localhost:8080/api/data/employees"
    },
    "kudos" : {
      "href" : "http://localhost:8080/api/data/kudos"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/data/profile"
    }
  }
}
```

This representation shows the **_links** property in the JSON object showing all of the child entities from **/api/data**. This is a common occurrence and should be used for discovery purposes in client applications. Let's take a look at one of the child entities, **employees**:  

```json
{
  "_embedded" : {
    "employees" : [ {
      "firstName" : "Cookie",
      "lastName" : "Monster",
      "title" : "QA",
      "emailAddress" : "cookiemonster@mailinator.com",
      "workPhone" : "111-111-1111",
      "cellPhone" : "444-444-4444",
      "biography" : "blurb here",
      "profileImageUrl" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/data/employees/1"
        },
        "locale" : {
          "href" : "http://localhost:8080/api/data/employees/1/locale"
        }
      }
    }, {
      "firstName" : "Big",
      "lastName" : "Bird",
      "title" : "QA",
      "emailAddress" : "bigbird@mailinator.com",
      "workPhone" : "222-222-2222",
      "cellPhone" : "555-555-5555",
      "biography" : "blurb here",
      "profileImageUrl" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/data/employees/2"
        },
        "locale" : {
          "href" : "http://localhost:8080/api/data/employees/2/locale"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/data/employees"
    },
    "profile" : {
      "href" : "http://localhost:8080/api/data/profile/employees"
    },
    "search" : {
      "href" : "http://localhost:8080/api/data/employees/search"
    }
  }
}
```

In the employees route **/api/data/employees**, the server has returned the familiar **_links** object in the JSON object, as well as an **_embedded** object containing information about **employees**. **_embedded** simply means that the representation returned above is only a representation of the employee objects. The real object exists as a child of this route.  
For example, **employee** with id **1** exists at **/api/data/employees/1**. As we can see below, the object is represented as a pure JSON object, not "_embedded".  

```json
{
  "firstName" : "Cookie",
  "lastName" : "Monster",
  "title" : "QA",
  "emailAddress" : "cookiemonster@mailinator.com",
  "workPhone" : "111-111-1111",
  "cellPhone" : "444-444-4444",
  "biography" : "blurb here",
  "profileImageUrl" : null,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/data/employees/1"
    },
    "locale" : {
      "href" : "http://localhost:8080/api/data/employees/1/locale"
    }
  }
}
```  

##Useful Documents
* [Consuming Everest](docs/consuming-everest.md)  
This document explains how to properly consume the Everest web service for use in your applications or workplace.
* [Deploying Everest](docs/deploying-everest.md)  
This document explains how to properly install/deploy the Everest web service for use in your applications or workplace.

##About
As mentioned in the introduction to above, Everest was created for learning and new technologies and to address server-side solutions. The project is intended to be open-source and extensible for other organizations looking for an out-of-the-box ready RESTful web service to share workplace data.

##Technologies
* [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* [MySQL Server](http://www.mysql.com)
* [Spring Boot](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Data JPA](http://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
* [Spring Data Rest](http://docs.spring.io/spring-data/rest/docs/current/reference/html/)
* [Spring Security](http://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/)
* [Spring HATEOAS](http://docs.spring.io/spring-hateoas/docs/current/reference/html/)
