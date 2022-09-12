# Simple TODO App with Couchbase

You can find built docker images on my docker hub : https://hub.docker.com/repository/docker/yilmzmesut/simple-todo-app

If you want to test and build project in your local, you need to run just :
<code>
'docker-compose up'
</code>.
This will build app and create couchbase server and you can access project APIs via Swagger UI that its link is shared below. 

Run test suite via :
<code>mvn clean test</code>.

To see and test API's, Swagger UI is provided: http://localhost:8080/swagger-ui/#/

Postman collection API requests is provided for testing API's : https://github.com/yilmzmesut/simple-todo-app/tree/main/postman_collection

Also, you can reach project via [this link](https://todo-app-yilmzmesut.herokuapp.com/swagger-ui/#/) which is deployed on heroku.


#Testing

First of all, except /user/sign-up and /user/sign-in APIs, all APIs require valid bearer token.

1. You must create an account on project via POST /user/sign-up API.
2. After successfully creating the account, you can sing in via POST /user/sign-in API. This API returns bearer token. With this token you can access other APIs.
3. Now, you can create todo task via PUT /task and taskDto body.
4. If you want to get all your task, you should go to GET /task API. This API returns all your todo tasks.