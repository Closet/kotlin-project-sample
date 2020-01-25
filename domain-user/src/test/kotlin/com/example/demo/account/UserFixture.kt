package com.example.demo.account

import com.example.demo.commons.vo.MobileNumber
import com.example.demo.domain.account.dto.Account

class UserFixture {
    companion object {
        fun testCaseForCreateAccountRequest() =
                Account.CreateAccountRequest(
                        "테스트",
                        "강남구",
                        MobileNumber("010-0000-0000")
                )

        fun testCaseForReadAccountRequest() =
                Account.ReadAccountRequest(
                        "테스트",
                        MobileNumber("010-0000-0000")
                )

        fun testCaseForChangeAccountRequest() =
                Account.UpdateAccountRequest(
                        "uuid",
                        "강남구",
                        MobileNumber("010-0000-0000")
                )
    }
}