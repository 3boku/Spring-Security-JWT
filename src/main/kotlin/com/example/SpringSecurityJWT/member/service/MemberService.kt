package com.example.SpringSecurityJWT.member.service

import jakarta.persistence.Transient
import jakarta.transaction.Transactional
import com.example.SpringSecurityJWT.member.dto.MemberDtoRequest
import com.example.SpringSecurityJWT.member.entity.Member
import com.example.SpringSecurityJWT.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService (
    private val memberRepository: MemberRepository
) {
    fun sighUp(memberDtoRequest: MemberDtoRequest): String {
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            return "이미 등록된 ID 입니다."
        }

        member = Member(
            null,
            memberDtoRequest.loginId,
            memberDtoRequest.password,
            memberDtoRequest.name,
            memberDtoRequest.birthDate,
            memberDtoRequest.gender,
            memberDtoRequest.email
        )

        memberRepository.save(member)

        return "회원가입이 완료되었습니다."
    }
}