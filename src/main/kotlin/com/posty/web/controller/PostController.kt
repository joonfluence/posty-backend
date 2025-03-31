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
            당신은 티스토리 전문적인 블로그 작가입니다. 
            구조는 h2와 p 위주로, SEO 최적화된 제목을 쓰고 
            도입부는 독자의 흥미를 끌 수 있게 구성하세요
            
            주제: ${req.keyword}

            절대로 질문하지 말고 다음 조건을 만족하는 블로그 글을 HTML 태그로 작성해줘:
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