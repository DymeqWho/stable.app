# Stable App

Applikationen "Stable App" används för att hantera ryttare, hästar, ryttare-häst-par, instruktörer och grupper som består av par och instruktörer. All data lagras i en MySQL-databas, där CRUD-operationer (Create, Read, Update, Delete) kan utföras.

## Arkitektur

Applikationen är byggd på **MVCS** (Model-View-Controller-Service) mönstret, vilket möjliggör separation av affärslogik, hantering av förfrågningar och interaktion med databasen. Här är en beskrivning av de olika paketen:

- **Paketet entities**: Ansvarar för att skapa entiteter som används i databasen och affärslogiken. Entiteter kan skapas med hjälp av builders.
- **Paketet controller**: Hanterar HTTP-förfrågningar från användare och svarar med lämpliga data.
- **Paketet service**: Innehåller affärslogik, tolkar förfrågningar från kontrollern och hanterar operationer mot databasen genom att anropa rätt entiteter.
- **Paketet database**: Hanterar anslutningen till databasen för att utföra CRUD-operationer.

## Databasinställning

Innan du börjar använda applikationen måste du konfigurera databasen. Gör följande:

1. Skapa ett nytt schema i MySQL och namnge det enligt dina önskemål (t.ex. `databasename`).

2. Ställ in rätt värden i `application.properties`-filen:

   ```properties
   spring.application.name=stable.app
   spring.datasource.url=jdbc:mysql://localhost:3306/databasename
   spring.datasource.username=username
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   
## Installation
För att installera applikationen, kör följande kommando:

``mvn clean install``

## Testa applikationen
Applikationen kan testas med Postman på en dedikerad port. Nedan finns exempel på endpoints för att hantera hästar:

## Endpoints för Horse
Hämta alla hästar:
``GET /api/horses``

Skapa en ny häst:
``POST /api/horses``

Exempel JSON:
```
{
"name": "Flash",
"description": "testing horse"
}
```

Hämta en häst:
``GET /api/horses/{name}``

Uppdatera en häst:
``PUT /api/horses/update/{name}``

Ta bort en häst:
``DELETE /api/horses/{name}``

## Endpoints för Rider
Hämta alla ryttare:
``GET /api/riders``

Skapa en ny ryttare:
``POST /api/riders``

```
{    
    "name": "John Doe",
    "description": "testing rider"
}
```

Hämta en ryttare:
``GET /api/riders/{name}``

Uppdatera en ryttare:
``PUT /api/riders/update/{name}``

Ta bort en ryttare:
``DELETE /api/riders/{name}``

## Endpoints för Instructor
Hämta alla instruktörer:
``GET /api/instructors``

Skapa en ny instruktör:
``POST /api/instructors``

```
{
    "name": "Master Of Disaster",
    "description": "testing master"
}
```

Hämta en instruktör:
``GET /api/instructors/{name}``

Uppdatera en instruktör:
``PUT /api/instructors/update/{name}``

Ta bort en instruktör:
``DELETE /api/instructors/{name}``

## Endpoints för Pair
Hämta alla par:
``GET /api/pairs``

Skapa ett nytt par:
``POST /api/pairs``

```
{
  "description": "testing pair",
  "rider": {
    "name": "John Doe"    
  },
  "horse": {
    "name": "Flash"  
  } 
}
```

Hämta ett par:
``GET /api/pairs/{id}``

Uppdatera ett par:
``PUT /api/pairs/update/{id}``

Ta bort ett par:
``DELETE /api/pairs/{id}``

## Endpoints för Group
Hämta alla grupper:
``GET /api/groups``

Skapa en ny grupp:
``POST /api/groups``

```
{
    "name": "Testers",
    "description": "testing group",
    "instructor": {
        "name": "Master Of Disaster"
    },
    "pairs": [
        {
            "id": 1 
        }
    ]
}
```

Hämta en grupp:
``GET /api/groups/{name}``

Uppdatera en grupp:
``PUT /api/groups/update/{name}``

Ta bort en grupp:
``DELETE /api/groups/{name}``

## Licens
Projektet licensieras under **MIT License**.

## Kontakt
Vid frågor eller förslag, vänligen kontakta mig i ett privat meddelande.

Författare: *Dymitr Misiejuk*

