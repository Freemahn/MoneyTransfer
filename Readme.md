## How to build
`gradle fatJar`  
In *build/libs* will be jar file to run

## How to run
`java -jar money-transfer-all-1.0-SNAPSHOT.jar`  
url - `localhost:4567`
## How to use
Each user has default account with 10000 RUB  
After running, there will be created three users.
First user will have two additional accounts, one with euro

GET `/users` - get all users  
GET `/users/:id` - get user  
POST `/users` - create new user  
PUT `/users/:id` - update existing user  
DELETE `/users/:id` - delete user  

GET `/accounts` - get all accounts  
GET `/accounts/:accountNumber` - get account by its number  
POST `/accounts` - create new accounts  
PUT `/accounts/:accountNumber` - update existing accounts  
DELETE `/accounts/:accountNumber` - delete accounts  

GET `/accounts/:accountNumber/transfers` - get transfers history for account   
GET `/accounts/:accountNumber/transfers/:id` - get transfer by its number  
POST `/accounts/:accountNumber/transfers` - create new transfer  

User body example  
`{"firstName":"Ivan","lastName":"Ivanov","email":"ivan.ivanov@gmail.com","accounts":[]}`

Account body example   
`{"name":"EUR account for savings","currency":"EUR","amount":"100"}`

Transfer body example   
`{"accountNumberTo": "1000005", "amount":100, "currency": "EUR"}`

GET `http://localhost:4567/users` 
`{"status":"SUCCESS","data":[{"id":"1","firstName":"Pavel","lastName":"Gordon","email":"gordon.pav@gmail.com","accounts":[{"name":"My first account for rubles","accountNumber":"1000001","fundsAvailable":{"amount":1000.0,"currency":"RUB"},"transfers":[],"currency":"RUB"},{"name":"EUR savings for Europe trips","accountNumber":"1000002","fundsAvailable":{"amount":500.0,"currency":"EUR"},"transfers":[],"currency":"EUR"}]},{"id":"2","firstName":"Ilya","lastName":"Lyamkin","email":"ilya.lyamkin@gmail.com","accounts":[{"name":"Default account","accountNumber":"1000004","fundsAvailable":{"amount":0.0,"currency":"RUB"},"transfers":[],"currency":"RUB"}]},{"id":"3","firstName":"Michail","lastName":"Kongoev","email":"mk.neo@yandex.ru","accounts":[{"name":"Default account","accountNumber":"1000005","fundsAvailable":{"amount":0.0,"currency":"RUB"},"transfers":[],"currency":"RUB"}]}]}`

GET `http://localhost:4567/accounts/1000001`  
`{"status":"SUCCESS","data":{"name":"My first account for rubles","accountNumber":"1000001","fundsAvailable":{"amount":1000.0,"currency":"RUB"},"transfers":[],"currency":"RUB"}}`

POST `http://localhost:4567/accounts/1000001/transfers`   body - `{"accountNumberTo": "1000005", "amount":100, "currency": "EUR"}`

`{"status":"SUCCESS","data":{"id":"1","amount":{"amount":10.0,"currency":"EUR"},"accountFrom":{"name":"My first account for rubles","accountNumber":"1000001","fundsAvailable":{"amount":286.34000000000003,"currency":"RUB"},"transfers":[],"currency":"RUB"},"accountTo":{"name":"Default account","accountNumber":"1000005","fundsAvailable":{"amount":713.66,"currency":"RUB"},"transfers":[],"currency":"RUB"},"notes":"","status":"PROCEEDED","createdAt":{"dateTime":{"date":{"year":2018,"month":3,"day":20},"time":{"hour":12,"minute":17,"second":53,"nano":328000000}},"offset":{"totalSeconds":10800},"zone":{"id":"Europe/Moscow"}}}}`