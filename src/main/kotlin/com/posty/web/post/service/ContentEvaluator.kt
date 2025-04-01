package com.posty.web.post.service

import com.posty.web.client.CustomChatClient
import com.posty.web.domain.post.entity.PostEntity
import com.posty.web.domain.post.entity.PostStatus
import com.posty.web.domain.post.repository.PostRepository
import com.posty.web.post.controller.GenerateRequest
import com.posty.web.post.event.EvaluateContentEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class ContentEvaluator(
    private val chatClient: CustomChatClient,
    private val postRepository: PostRepository,
) {
    @Async
    @EventListener
    fun handle(event: EvaluateContentEvent) {
        if (event.retryCount < 2) {
            val response = chatClient.evaluate(event.html)
            if (response.total < 70) {
                println("재생성 필요: ${event.keyword}")
                postRepository.save(
                    PostEntity(
                        keyword = event.keyword,
                        content = event.html,
                        score = response.total,
                        status = PostStatus.FAILED,
                        retryCount = event.retryCount,
                        failureReason = "$event.retryCount 회 재생성 실패"
                    )
                )

                chatClient.call(
                    GenerateRequest(
                        event.keyword,
                        event.retryCount + 1
                    )
                )
            } else {
                println("✅ 통과: ${event.html} [$response.total 점]")
                postRepository.save(
                    PostEntity(
                        keyword = event.keyword,
                        content = event.html,
                        score = response.total,
                        status = PostStatus.SUCCESS,
                        retryCount = event.retryCount,
                    )
                )
            }
        }
    }
}