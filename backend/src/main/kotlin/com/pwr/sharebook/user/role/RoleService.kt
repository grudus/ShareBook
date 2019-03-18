package com.pwr.sharebook.user.role

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class RoleService

@Autowired
constructor(private val roleRepository: RoleRepository)
{

    fun getDefaultRole(): RoleEntity =
            roleRepository.findByName(RoleType.STUDENT) ?: throw IllegalStateException("Cannot find default role")

}
