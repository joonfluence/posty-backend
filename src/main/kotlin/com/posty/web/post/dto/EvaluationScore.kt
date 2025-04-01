package com.posty.web.post.dto

data class EvaluationScore(
    val structure: Int,
    val info: Int,
    val natural: Int,
    val seo: Int,
    val total: Int,
)