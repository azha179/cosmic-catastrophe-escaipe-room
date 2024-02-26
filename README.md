# Cosmic Catastrophe - EscAIpe Room - SOFTENG 206

Escape room-themed game built in JavaFX as part of a university course group project. GPT3 AI is utilised in the game using a cat character whose dialogue is generated by the AI.

## Installation
Java is required on the system.
Maven is used to run the program.

## To setup OpenAI's API
The project requires an OpenAI token to use the GPT API. As a university project, the API setup is handled by the professors, which requires a university email and token key to be used. If you wish to use the API with a token, feel free to contact me for assistance.

Original Instructions:
- add in the root of the project (i.e., the same level where `pom.xml` is located) a file named `apiproxy.config`

  ```
  email: "upi123@aucklanduni.ac.nz"
  apiKey: "YOUR_KEY"
  ```
  These are your credentials to invoke the OpenAI GPT APIs

## To run the game

`./mvnw clean javafx:run`
