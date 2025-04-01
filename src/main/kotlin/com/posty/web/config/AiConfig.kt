package com.posty.web.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig(
    @Qualifier("ollamaChatModel") private val ollama: ChatModel,
    @Qualifier("openAiChatModel") private val openai: ChatModel
) {
    @Bean
    fun openAiChatClient(): ChatClient {
        return ChatClient.create(openai)
    }

    @Bean
    fun ollamaAiChatClient(): ChatClient {
        return ChatClient.create(ollama)
    }
}
