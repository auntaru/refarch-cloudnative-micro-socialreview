# Spring Boot app Integration with IBM Cloudant
https://developer.ibm.com/tutorials/getting-started-with-microservices-using-spring-boot-and-cloudant/

IBM Cloudant / Apache CouchDB 

https://www.ibm.com/cloud/cloudant
https://cloud.ibm.com/docs/Cloudant?topic=Cloudant-couchdb-and-cloudant
https://medium.com/codait/cloudant-and-couchdb-replication-with-couchreplicate-79ea6e898e6e

https://www.youtube.com/watch?v=Vh7EDEF81NY&feature=emb_logo
https://www.openshift.com/blog/openshift-commons-briefing-using-apache-couchdb-operator-for-data-portability-josh-mintz-and-will-holley-ibm

https://www.youtube.com/watch?v=aOE90VAVOcU
https://www.youtube.com/watch?v=5co1CuTPtkg
https://www.youtube.com/watch?v=nlqv9Np3iAU

https://cloud.ibm.com/catalog?category=databases#services
https://cloud.ibm.com/catalog/services/cloudant#

IBM Cloudant is a fully managed JSON document database that offers independent serverless scaling of provisioned throughput capacity and storage. Cloudant is compatible with Apache CouchDB and accessible through a simple to use HTTPS API for web, mobile, and IoT applications.

HA/DR: All Cloudant JSON documents are stored in triplicate for in-region HA/DR.  In regions that support availability zones (Dallas, Washington DC, London, Frankfurt, Tokyo, Sydney), documents are stored across three separate availability zones.

*This project is part of the 'IBM Cloud Native Reference Architecture' suite, available at
https://github.com/ibm-cloud-architecture/refarch-cloudnative*

## Introduction

This project is built to demonstrate how to build a Spring Boot Microservices application to access IBM Cloudant NoSQL database. It provides basic operations of saving and querying reviews from database as part of Social Review function. The project covers following technical areas:

 - Leverage Spring Boot framework to build Microservices application
 - Use IBM Cloudant Spring Boot Starter to access Cloudant database

## Provision Cloudant Database in IBM Cloud:

Login to your IBM Cloud console  
Open browser to create Cloudant Service using this link [https://new-console.ng.bluemix.net/catalog/services/cloudant-nosql-db](https://new-console.ng.bluemix.net/catalog/services/cloudant-nosql-db)  
Name your Cloudant service name like `refarch-cloudantdb`  
For testing, you can select the "Shared" plan, then click "Create"  
Once created, open the credential tab to note down your Cloudant Service credential, for example:

```
{
 "username": "3xxxx-44f0d2add79e-bluemix",
 "password": "xxxxxxxxxxxxxxxxxxxxxx",
 "host": "xxxxx-bluemix.cloudant.com",
 "port": 443,
 "url": "https://xxxxx-bluemix.cloudant.com"
}
```
Then, click the "Launch" button to open the Cloudant management console.   

You can close the console now.

## Run the application locally:

- Create Cloudant database

    You can either use Cloudant local or IBM Cloudant managed account. Once you have cloudant setup, update the src/resources/application.yml file for the Cloudant credential:

    ```yml
    # Cloudant Configuration
    cloudant:
        db: socialreviewdb
        username: {your_cloudant_username}
        password: {your_cloudant_password}
        host: {your_cloudant_host}
    ```

- Run following command to build and test the application:

    ```
    $ ./gradlew build
    ```

- To run the app:

    ```
    $ java -jar build/libs/micro-socialreview-0.1.0.jar
    ```

- To run integration test case:

    ```
    $ ./gradlew test
    ````

- Validate the application:

    Go to the reviews endpoint to see all reviews in the database: http://localhost:8080/micro/review

    You can use Chrome POSTMAN to insert a new review document using the following sample content:

    ```json
    {
        "comment": "Nice Product",
        "itemId": 13402,
        "rating": 5,
        "reviewer_email": "gangchen@us.ibm.com",
        "reviewer_name": "Gang Chen",
        "review_date": "06/08/2016"
    }
    ```

    Or you can use the following curl command:
    ```
    curl --request POST\
        --url 'http://localhost:8080/micro/review' \
        --header 'accept: application/json' \
        --header 'content-type: application/json' \
        --data @- <<'EOF'
        {"comment": "Nice product",
        "itemId": 13402,
        "rating": 3,
        "reviewer_email":"gangchen@us.ibm.com",
        "reviewer_name": "Gang Chen",
        "review_date": "12/31/2017"}' 
        EOF
    ```
