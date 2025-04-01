package com.posty.web.post.controller

import com.posty.web.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/generate")
class PostController(
    private val postService: PostService,
) {
    @PostMapping
    fun generate(@RequestBody req: GenerateRequest): ResponseEntity<String> {
        val generatedContent = postService.generate(req)
        return ResponseEntity.ok(generatedContent)
    }
}

data class GenerateRequest(
    val keyword: String,
    val retryCount: Int = 0,
)