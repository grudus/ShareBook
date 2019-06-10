package com.pwr.sharebook.activity

import com.pwr.sharebook.comment.CommentEntity
import com.pwr.sharebook.comment.CommentRepository
import com.pwr.sharebook.group.GroupEntity
import com.pwr.sharebook.group.post.PostEntity
import com.pwr.sharebook.group.post.PostRepository
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class ActivityServiceTest {

    @Mock
    private lateinit var commentRepository: CommentRepository

    @Mock
    private lateinit var postRepository: PostRepository

    private lateinit var activityService: ActivityService

    @Before
    fun init() {
        activityService = ActivityService(postRepository, commentRepository)
    }


    @Test
    fun shouldFindActivityForUser() {
        Mockito.`when`(commentRepository.findAllByCreatedByIdAndCreatedAtBetween(anyLong(), any(LocalDateTime::class.java), any(LocalDateTime::class.java)))
                .thenReturn(listOf(randomCommentAt(LocalDate.now()), randomCommentAt(LocalDate.now())))

        Mockito.`when`(postRepository.findAllByUserEntityIdAndCreatedAtBetween(anyLong(), any(LocalDateTime::class.java), any(LocalDateTime::class.java)))
                .thenReturn(listOf(randomPostAt(LocalDate.now())))


        val userActivity = activityService.getUserActivity(1L, LocalDateTime.now().year)

        val currentMonth = LocalDate.now().month.ordinal

        assertEquals(12, userActivity.activityPerMonth.size)
        assertEquals(2, userActivity.activityPerMonth.get(currentMonth).numberOfComments)
        assertEquals(1, userActivity.activityPerMonth.get(currentMonth).numberOfPosts)
    }

    private fun randomCommentAt(date: LocalDate): CommentEntity {
        return CommentEntity(Random.nextLong(), date.atStartOfDay(), randomText(), PostEntity(), UserEntity())
    }

    private fun randomPostAt(date: LocalDate): PostEntity {
        return PostEntity(Random.nextLong(), date.atStartOfDay(), randomText(), GroupEntity(), UserEntity(), emptyList(), emptyList())
    }
}
