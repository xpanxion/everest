#Everest
Everest is a HATEOAS compliant RESTful web service that exposes custom workplace metrics and data. The project was born as an opportunity for employees to learn more about specific development technologies, as well as to address a server-side need for a few internal applications.  Read more about the Everest Project in the [About section](#about) below.  

##Build Statuses
[![Build Status](https://travis-ci.org/xpanxion/everest.svg)](https://travis-ci.org/xpanxion/everest) [![Coverage Status](https://coveralls.io/repos/xpanxion/everest/badge.svg?branch=master&service=github)](https://coveralls.io/github/xpanxion/everest?branch=master) master

##Install
1) Assuming that MySQL server has been installed, first create a database schema using the following.  This only needs to be done once.  
```mysql
CREATE DATABASE everest;
```

2) Build the project using maven:  
```bash
cd PROJECT_DIRECTORY
mvn install
```  
This will build the project's **everest.jar** file to PROJECT_DIRECTORY/target/everest.jar.  

3) Next, the application can be deployed locally by running the following in a terminal session:  
```bash
java -jar everest.jar
```

At this point the application should be up and running at http://127.0.0.1:8080/api.  

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