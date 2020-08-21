# leanpay
Simple credit calculator application

Request - POST METHOD:
http://localhost:8083/installment-plan

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
        },
        {
            "month": 4,
            "paymentAmount": 102.31
        },
        {
            "month": 5,
            "paymentAmount": 102.31
        },
        {
            "month": 6,
            "paymentAmount": 102.31
        },
        {
            "month": 7,
            "paymentAmount": 102.31
        },
        {
            "month": 8,
            "paymentAmount": 102.31
        },
        {
            "month": 9,
            "paymentAmount": 102.31
        },
        {
            "month": 10,
            "paymentAmount": 102.31
        }
    ]
}
