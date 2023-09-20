package nz.ac.auckland.se206;

import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.openai.ApiProxyException;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionResult.Choice;

/** Class to help with game actions involving the GPT model. */
public class GptActions {
  /**
   * Append message to the a text area.
   *
   * @param msg the message to append
   * @param textArea the text area to append to
   */
  public static void appendChatMessage(ChatMessage msg, javafx.scene.control.TextArea textArea) {
    textArea.appendText(msg.getContent() + "\n\n");
  }

  /**
   * Runs the GPT model with a given chat message.
   *
   * @param msg the chat message to process
   * @param chatCompletionRequest the chat completion request
   * @return the response chat message
   * @throws ApiProxyException if there is an error communicating with the API proxy
   */
  public static ChatMessage runGpt(ChatMessage msg, ChatCompletionRequest chatCompletionRequest)
      throws ApiProxyException {
    chatCompletionRequest.addMessage(msg);
    try {
      ChatCompletionResult chatCompletionResult = chatCompletionRequest.execute();
      Choice result = chatCompletionResult.getChoices().iterator().next();
      chatCompletionRequest.addMessage(result.getChatMessage());
      return result.getChatMessage();
    } catch (ApiProxyException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Clears messages from the chat completion request.
   *
   * @param chatCompletionRequest the chat completion request
   */
  public static void clearMessages(ChatCompletionRequest chatCompletionRequest) {
    chatCompletionRequest.getMessages().clear();
  }
}
