package com.posty.web.domain.post.repository

import com.posty.web.domain.post.entity.PostEntity
import com.posty.web.domain.post.entity.PostStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {
    fun findByStatus(status: PostStatus): List<PostEntity>
    fun findByKeyword(keyword: String): List<PostEntity>
}
