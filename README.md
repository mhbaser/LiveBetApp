
# Live Bet Application

This project is a live betting application where users can place multiple bets on the same match and dynamically changing odds are applied to each match. It uses **Spring Boot 3.x**, **Java 17/21**, and **H2 Database** as an in-memory database.

## Features

1. **Dynamic Match Odds**:
    - New matches can be added to the match bulletin.
    - For each added match, odds for the home team win, draw, and away team win are dynamically generated.
    - The odds change every second.

2. **Real-Time Match Display**:
    - Users can view all matches and their dynamically changing odds in real-time, sorted from oldest to newest.

3. **Betting**:
    - A maximum of 500 bets can be placed on a match at any time.
    - The odds at the time of placing a bet will remain unchanged for that bet.
    - Each individual bet has a configurable timeout value (default 2 seconds).

4. **API and Security**:
    - RESTful APIs developed for match management and betting.
    - HTTP Basic Authentication is used for secured endpoints.
    - CSRF protection is disabled for ease of use in API requests.


## Technologies Used

- **Java 17/21**: Core programming language.
- **Spring Boot 3.x**: Backend framework for building RESTful APIs.
- **H2 Database**: In-memory database for testing and development purposes.
- **Spring Security**: For handling authentication using HTTP Basic Authentication.
- **Lombok**: Used to reduce boilerplate code.
- **MapStruct**: For mapping between objects.

## Endpoints

### Public Endpoints
- `GET /api/public/matches`: Retrieves all matches and their odds.
- `POST /api/matches/add`: Adds a new match to the bulletin (accessible without authentication).

### Secured Endpoints (Authentication Required)
- `POST /api/bets/place`: Places a bet on a match.

### Admin Endpoints
- `POST /api/admin/matches/add`: Add matches with admin authentication.

## Installation and Running the Application

### Prerequisites
- Java 17 or newer installed.
- Maven installed.



### Installation

Clone the project by running the following command in your terminal:
`git clone https://github.com/mhbaser/LiveBetApp.git`

Then navigate to the project directory:

`cd live-bet-app`

Install the required dependencies using
Maven: `mvn clean install`

Start the database (if using H2 database). The database URL will be:
`jdbc:h2:mem:testdb`
Run the application using the following command:
`mvn spring-boot:run`
You can access the application at:
`http://localhost:8081`


### Usage

#### Place a Bet

To place a bet, you can send a **POST** request to `api/bets/place` with the following JSON body:

```
'{
    "matchId": 1, 
    "betType": "HOME_WIN", 
    "amount": 100
}'
```

#### Place a Bet

To place a bet, you can send a **POST** request to `/api/matches/add` with the following JSON body:

```
'{
    "league": "Premier League", 
    "homeTeam": "Manchester United", 
    "awayTeam": "Chelsea", 
    "homeWinOdds": 1.8, 
    "drawOdds": 3.2, 
    "awayWinOdds": 2.5, 
    "matchStartTime": "2024-10-01T15:00:00"
}'
```


### Testing

To run the unit and integration tests, use the following command in your terminal:

`mvn test`

The project uses JUnit and Mockito for unit testing.

API Documentation

The Swagger API documentation is available at:

`http://localhost:8081/swagger-ui.html`

### Dependencies

The project uses the following dependencies:

- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security
- JUnit 5
- Mockito
- Contributing