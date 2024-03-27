README
###  spring security spring boot 3  tokens  postgresSql
###  registro   
POST localhost:6060/auth/signup

POST localhost:6060/auth/signin

```json
{
    "name": "jonnyjaimes",
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023",
    "role": "USER",
    "codigoUniversidad": "1151743",
    "semestreActual":10,
    "edad": 23,
    "direccionResidencia":"cucuta calle 2",
    "celular":"3133713137"
}
{
    "name": "jonnyadmin",
    "email": "admin@ufps.co",
    "password": "ufps2023",
    "role": "Admin",
    "codigoUniversidad": "1151743",
    "semestreActual":10,
    "edad": 23,
    "direccionResidencia":"cucuta calle 2",
    "celular":"3133713137"
}



{
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023"
}

