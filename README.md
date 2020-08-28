# leanpay
Simple credit calculator application

1. Clone the projcet  
2. Build the Docker container with command  
	“mvn package dockerfile:build”  
3. Run command "docker-compose up" in the root directory  
4. Import postman collections and trigger the API's  
5. Check result in DB (it is recommended to use an "adminer" container that is running)  
	Link: http://localhost:8080  
	username: user  
	password: user  
	After login choose "leanpaydb"    
  
Note: If you don't want to use docker containers you should change application.properties to use connection string and credentials for standard connection without docker, in that case, you should build and run the application on the standard way(mvn clean install & run as a spring boot application)   
	username: root    
	password:    
	db name:  "leanpaydb"  
  	
API's:  
http://localhost:8083/installment-plan or  
http://localhost:8083/installment-plan-detailed  

Method: POST  
Request body  
{  
    "amount":1000.00,  
    "numberOfMonths":10,  
    "monthlyInterestPercent": 5.00  
} 

Expected response for installment-plan API:    
{  
    "amount": 1000.0,  
    "totalAmount": 1023.06,  
    "interestAmount": 23.06,  
    "items": [  
        {  
            "month": 1,  
            "paymentAmount": 102.31,  
        }...  
    ]  
} 

Expected response for installment-plan-detailed API:  
{  
    "amount": 1000.0,  
    "totalAmount": 1023.06,  
    "interestAmount": 23.06,  
    "items": [  
        {  
            "month": 1,  
            "paymentAmount": 102.31,  
            "principalAmount": 98.14,  
            "interestAmount": 4.17,  
            "balanceOwed": 901.86  
        }...  
    ]  
}  
