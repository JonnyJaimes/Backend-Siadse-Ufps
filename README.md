README
###  spring security spring boot 3  tokens  postgresSql
###  registro   
POST localhost:6060/auth/signup

```json
{
    "name": "jonnyjaimes",
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023",
    "role": "USER",
    "codigoUniversidad": "1151743"
}
```json

POST localhost:6060/auth/signin
```json
{
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023"
}
```json

