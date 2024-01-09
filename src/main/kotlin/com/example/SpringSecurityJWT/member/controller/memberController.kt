package com.example.SpringSecurityJWT.member.controller

import com.example.SpringSecurityJWT.member.dto.MemberDtoRequest
import com.example.SpringSecurityJWT.member.service.MemberService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class memberController (
    private val memberService: MemberService
) {
    fun sighUp(@RequestBody memberDtoRequest: MemberDtoRequest): String  {
        return memberService.sighUp(memberDtoRequest)
    }
}