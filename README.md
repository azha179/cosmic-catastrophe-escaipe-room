# Cosmic Catastrophe - EscAIpe Room

Escape room-themed game built in JavaFX as a university group project for SOFTENG 206. The game features a cat character whose dialogue is generated using the OpenAI GPT API.

## Requirements

- Java
- Maven

## Setup

### OpenAI API

The game requires an OpenAI API key to generate GPT dialogue.

1. Obtain a personal API key from [platform.openai.com/api-keys](https://platform.openai.com/api-keys)
2. Create a file named `apiproxy.config` in the project root (same level as `pom.xml`)
3. Add your key to the file:

   ```
   apiKey: "sk-..."
   ```

## Running the game

```
./mvnw clean javafx:run
```

## Debugging

```
./mvnw clean javafx:run@debug
```

Then in VS Code, open **Run & Debug** and select **Debug JavaFX**.
