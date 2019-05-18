package com.pwr.sharebook.group.post

import com.pwr.sharebook.comment.CommentDto

data class PostWithCommentsDto(val post: GroupPostDto, val comments: List<CommentDto>)
