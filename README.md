# Spring Boot app Integration with IBM Cloudant

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
