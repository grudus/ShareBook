package com.pwr.sharebook.activity

import com.pwr.sharebook.comment.CommentEntity
import com.pwr.sharebook.comment.CommentRepository
import com.pwr.sharebook.group.post.PostEntity
import com.pwr.sharebook.group.post.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.Month
import javax.transaction.Transactional

@Service
@Transactional
class ActivityService
@Autowired
constructor(
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository
) {


    fun getUserActivity(userId: Long, year: Int): UserActivityResult {
        val from = LocalDateTime.of(year, Month.FEBRUARY, 1, 0, 0)
        val to = LocalDateTime.of(year, Month.DECEMBER, 31, 23, 59);

        val posts: List<PostEntity> = postRepository.findAllByUserEntityIdAndCreatedAtBetween(userId, from, to)
        val comments: List<CommentEntity> = commentRepository.findAllByCreatedByIdAndCreatedAtBetween(userId, from, to)
        val attachments = posts.filter { it.attachments != null && it.attachments.isNotEmpty() }

        val postsPerMonth: Map<Month, Int> = posts.groupBy { it.createdAt!!.month }.mapValues { it.value.size }
        val commentsPerMonth: Map<Month, Int> = comments.groupBy { it.createdAt!!.month }.mapValues { it.value.size }
        val attachmentsPerMonth: Map<Month, Int> = attachments.groupBy { it.createdAt!!.month }.mapValues { it.value.size }

        val activities: List<UserActivity> =  Month.values().map { month ->
            UserActivity(
                    postsPerMonth[month] ?: 0,
                    commentsPerMonth[month] ?: 0,
                    attachmentsPerMonth[month] ?: 0,
                    month
            )
        }

        return UserActivityResult(activities)
    }
}
