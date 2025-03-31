package com.posty.web.controller

import com.posty.web.client.CustomChatClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generate")
class PostController(
    private val chatClient: CustomChatClient,
) {
    @PostMapping
    fun generate(@RequestBody req: GenerateRequest): ResponseEntity<String> {
        val prompt = """
            너는 티스토리 블로그 작가야.
            
            주제: ${req.keyword}

            다음 조건을 만족하는 블로그 글을 HTML 태그로 작성해줘:
            - SEO 최적화된 제목
            - 흥미로운 도입부
            - 소제목 2~3개, 각 소제목당 3~4문단
            - 요약 or 팁 포함
            - HTML 구조(h2, p, strong 등 포함)
        """.trimIndent()

        val response = chatClient.call(prompt)
        return ResponseEntity.ok(response)
    }
}

data class GenerateRequest(val keyword: String)
