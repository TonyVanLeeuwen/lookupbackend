EndPoints backend

http://localhost:8080/…

GET:

“authenticated”

“files/allfiles”

“/getfile/{id}

“/observation/id/{id}

“observation”

“observation/sortedbyvotesascending”

“/observation/sortedbyvotesdescending”

“observation/nearearthobject/id/{id}“

“users/user/name/{name}/

“users/user/observations/{name}

POST:

“authenticate”

“users/uploadobservationwithimage”

“/users/observation

“user” (to create a new user)

PATCH:

“users/observation/id/{id}” 

Users/user/id/{id}

DELETE:

“users/observation/id/{id}

“Users/user/id/{id}

Endpoints frontend

http://localhost:3000/…

Home: “/“

createnewObservation: “createNeo”

Login: “/login”

Observation: “observation”

List of observations: “postOverview”

profilepage: “profile”

Signup request: “signup”

/users/observation:

{
“title”: “Title of my beautiful observation”,
“textDescription”: “Text of my beautiful observation”
}

{
“title”: “Title of my beautiful observation”,
“textDescription”: “Text of my beautiful observation”,
“votes”: 4
}

/user

{
“name”: “iamanewuser”,
“passWord”: “thisismynewpassword”,
“emailAdress”: “thisismy@email.adress”
}

/users/user/{id}/authority

{
“username”: “existinguser”,
“authority”: “ADMIN”
}

users/observation/id/{id}

{
“title”: “I wanted a different title”,
“textDescription”: “I wanted some different text as well”
}

{
“title”: “I only wanted a different title”
}

{
“textDescription”: “I only wanted some different text”
}

{
“votes”: 1
}

Users/user/id/{id}

{
“name”: “nametoupdate”,
“passWord”: “passwordtoupdate”,
“emailAdress”: “email@adressto.update”
}

{
“name”: “nametoupdate”
}

{
“passWord”: “passwordtoupdate”
}

{
“emailAdress”: “email@adressto.update”
}

/users/user/{id}/authority

{
“username”: “existinguser”,
“authority”: “ADMIN”
}

Find the Frontendcode on https://github.com/TonyVanLeeuwen/LookUpReact
Find the Backendcode on https://github.com/TonyVanLeeuwen/lookupbackend

Get IntelliJ at https://www.jetbrains.com/idea/
Get Webstorm at https://www.jetbrains.com/webstorm/
Get PGAdmin & PostGres at https://www.postgresql.org