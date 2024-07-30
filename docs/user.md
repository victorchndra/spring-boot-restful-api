# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username": "victor",
  "password": "123",
  "name": "Victor Chandra"
}
```

Response Body (Success) :

```json
{
  "data": "OK"
}
```

Response Body (Failed) :

```json
{
  "errors": "Username must not blank, ???"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :
```json
{
  "username": "victor",
  "password": "123"
}
```

Response Body (Success) :
```json
{
  "data": {
    "token": "TOKEN",
    "expiredAt": 2313534231 //miliseconds
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors": "username or password wrong"
}
```

## Get User
Endpoint : GET /api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": {
    "username" : "victor",
    "name" : "Victor Chandra"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors": "Unauthorized"
}
```

## Update User
Endpoint : PATCH /api/users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "name" : "Victor", // put if only want to update name
  "password" : "new password" // put if only want to update password
}
```

Response Body (Success) :
```json
{
  "data": {
    "username" : "victor",
    "name" : "Victor Chandra"
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors": "Unauthorized"
}
```

## Logout User
Endpoint : DELETE /api/auth/logout

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data": "OK"
}
```