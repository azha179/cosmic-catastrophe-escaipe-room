# Cosmic Catastrophe - EscAIpe Room

Escape room-themed game built in JavaFX as a university group project for SOFTENG 206. The game features a cat character whose dialogue is generated using the OpenAI GPT API.

## Requirements

- Java
- Maven

## Setup

### OpenAI API

The game requires an OpenAI API key with sufficient credits to generate GPT dialogue.

1. Obtain a personal API key from [platform.openai.com/api-keys](https://platform.openai.com/api-keys)
2. Create a file named `apiproxy.config` in the project root (same level as `pom.xml`)
3. Add your key to the file:

   ```
   apiKey: "sk-..."
   ```

### Changing the GPT model

The default model is `gpt-4o-mini`. To use a different model, call `setModel()` on the `ChatCompletionRequest` in the relevant controller before executing it:

```java
new ChatCompletionRequest().setModel("gpt-4o").setN(1)...
```

The original university assignment used GPT-3 (`gpt-3.5-turbo`) via a university-managed proxy. Available models:

| Model | Notes |
|---|---|
| `gpt-4o-mini` | Default. Fast and cheap, good quality for most use cases. |
| `gpt-4o` | Best quality, higher cost per request. |
| `gpt-3.5-turbo` | Original model used in the assignment. Cheapest, but noticeably weaker. |

## Running the game

```
./mvnw clean javafx:run
```

## Debugging

```
./mvnw clean javafx:run@debug
```

Then in VS Code, open **Run & Debug** and select **Debug JavaFX**.
