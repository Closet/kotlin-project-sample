@file:Suppress("NonAsciiCharacters")

package com.example.demo.vo

import com.example.demo.commons.vo.MobileNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MobileNumberUnitTest {
    @Test
    fun `정상적인 폰 번호 입력 성공`() {
        val mobileNumber = MobileNumber.ofNumber("01000000001")
        Assertions.assertThat(mobileNumber.toString()).isEqualTo("01000000001")
    }

    @Test
    fun `비정상적인 폰 번호 입력`() {
        val phone = "010231231"

        Assertions.assertThatThrownBy {
            MobileNumber.ofNumber(phone)
        }.isInstanceOf(MobileNumber.NumberIsNotInvalidException::class.java)
                .hasMessage("전화번호가 올바르지 않습니다.")
    }
}