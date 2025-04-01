package com.posty.web.post.service

import com.posty.web.client.CustomChatClient
import com.posty.web.post.controller.GenerateRequest
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Paths

@Service
class BatchPostGenerator(
    private val chatClient: CustomChatClient,
) {

    fun runBatch() {
        val keywordFile = ClassPathResource("keywords.txt").file
        val outputDir = Paths.get("src/main/resources/output").toFile().also { it.mkdirs() }

        keywordFile.readLines()
            .filter { it.isNotBlank() }
            .forEachIndexed { index, keyword ->
                val content = chatClient.call(
                    GenerateRequest(keyword)
                )
                val safeFileName = "${"%02d".format(index + 1)}-${keyword.replace(" ", "_")}.html"
                File(outputDir, safeFileName).writeText(content, Charsets.UTF_8)

                println("✅ [$keyword] 글 생성 완료 → $safeFileName")
            }
    }
}
