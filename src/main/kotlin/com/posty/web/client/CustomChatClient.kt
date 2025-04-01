package com.posty.web.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.posty.web.post.controller.GenerateRequest
import com.posty.web.post.dto.EvaluationScore
import org.springframework.ai.chat.client.ChatClient
import org.springframework.stereotype.Component

@Component
class CustomChatClient(
    private val openAiChatClient: ChatClient,
    private val ollamaAiChatClient: ChatClient,
) {
    fun call(req: GenerateRequest): String {
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

        return openAiChatClient.prompt()
            .user(prompt)
            .call()
            .content().toString()
    }

    fun evaluate(content: String): EvaluationScore {
        val evaluationPrompt = """
        아래는 GPT가 생성한 블로그 글이야.

        ---
        $content
        ---

        이 글의 퀄리티를 100점 만점으로 평가해줘. 
        기준은 다음과 같아:
        1. 글의 구조 (제목, 도입, 소제목 구성) – 30점
        2. 정보의 충실도 – 30점
        3. 문장의 자연스러움 – 20점
        4. SEO 적합성 – 20점

        각 항목의 점수와 총점을 숫자로만 JSON 형식으로 반환해줘.

        예: { "structure": 25, "info": 27, "natural": 18, "seo": 15, "total": 85 }
    """.trimIndent()

        val text = ollamaAiChatClient.prompt()
            .user(evaluationPrompt)
            .call()
            .content().toString()

        val mapper = jacksonObjectMapper()
        val result: EvaluationScore = mapper.readValue(text)
        return result
    }
}