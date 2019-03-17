package com.pwr.sharebook


import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional

@ContextConfiguration(classes = [TestContext::class])
@WebAppConfiguration
@Transactional
@Rollback
@RunWith(SpringRunner::class)
@SpringBootTest
abstract class SpringBasedTest
