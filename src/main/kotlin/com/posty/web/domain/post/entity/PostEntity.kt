package com.posty.web.domain.post.entity

import jakarta.persistence.*

@Entity
@Table(name = "posts")
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val keyword: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    val score: Int,

    @Enumerated(EnumType.STRING)
    val status: PostStatus,

    val retryCount: Int = 0,

    @Column(columnDefinition = "TEXT")
    val failureReason: String? = null,

    val createdAt: Long = System.currentTimeMillis()
)

enum class PostStatus {
    SUCCESS, FAILED
}
