package com.posty.web.post.event

data class EvaluateContentEvent(
    val keyword: String,
    val html: String,
    val retryCount: Int,
)