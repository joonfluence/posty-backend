package com.posty.web.post.service

import com.posty.web.client.CustomChatClient
import com.posty.web.post.controller.GenerateRequest
import com.posty.web.post.event.EvaluateContentEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PostService(
    private val chatClient: CustomChatClient,
    private val eventPublisher: ApplicationEventPublisher
) {
    fun generate(req: GenerateRequest): String {
        val response = chatClient.call(req)
        eventPublisher.publishEvent(
            EvaluateContentEvent(
                req.keyword,
                response,
                req.retryCount,
            )
        )
        return response
    }
}