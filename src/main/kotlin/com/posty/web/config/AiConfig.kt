package com.posty.web.config

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.model.ChatModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AiConfig(
    private val chatModel: ChatModel
) {
    @Bean
    fun chatClient(): ChatClient {
        return ChatClient.create(chatModel)
    }
}
