package org.man.bootkotlin.repository

import org.man.bootkotlin.model.jpa.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface UserInfoJpaRepository : JpaRepository<UserInfoEntity, Int>