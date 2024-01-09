package com.example.SpringSecurityJWT.member.repository


import com.example.SpringSecurityJWT.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
interface MemberRepository : JpaRepository<Member, Long> {
    // ID 중복 검사를 위해 필요
    fun findByLoginId(loginId: String): Member?
}