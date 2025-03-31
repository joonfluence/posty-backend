package com.posty.web.client

import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Component

@Component
class CustomChatClient(
    private val chatClient: ChatClient,
) {
    fun call(prompt: String): String {
        return chatClient.prompt()
            .user(prompt)
            .call()
            .content().toString()
    }
}