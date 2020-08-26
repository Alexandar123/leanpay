# leanpay
Simple credit calculator application

1. Clone the project
2. Build the Docker container with command - "mvn package dockerfile:build"
3. run "docker-compose up" in the root directory! (docker-compose will do the work of bringing up both containers and supplying them with the configuration contained in docker-compose.yml)
4. Import postman collection and trigger API's
5. Check the results in the database (recommendation to use the running "adminer" container to check the connection) -
    localhost:8080 
        username: user 
        password: user

Request - POST METHOD:
http://localhost:8083/installment-plan or
http://localhost:8083/installment-plan-detailed

Request body:
{
    "amount":1000.00,
    "numberOfMonths":10,
    "monthlyInterestPercent": 5.00
}

Expected response:
{
    "amount": 1000.0,
    "totalAmount": 1023.06,
    "interestAmount": 23.06,
    "items": [
        {
            "month": 1,
            "paymentAmount": 102.31
        },
        {
            "month": 2,
            "paymentAmount": 102.31
        },
        {
            "month": 3,
            "paymentAmount": 102.31
        }...
    ]
}
