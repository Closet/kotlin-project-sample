@file:Suppress("NonAsciiCharacters")

package com.example.demo.vo

import com.example.demo.commons.vo.MobileNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MobileNumberUnitTest {
    @Test
    fun `정상적인 폰 번호 입력 성공`() {
        val mobileNumber = MobileNumber("01000000001")
        Assertions.assertThat(mobileNumber.toString()).isEqualTo("01000000001")
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "010231231",
        "01023123",
        "01023123",
        "010231234",
        "010231234010231234",
        "01035",
        "0231236",
        "0231243811",
        "023124381",
        "0324287919"
    ])
    fun `비정상적인 폰 번호 입력`(mobileNumberString: String) {
        Assertions.assertThatThrownBy {
            MobileNumber(mobileNumberString)
        }.`as`("${mobileNumberString}번호 테스트").isInstanceOf(MobileNumber.NumberIsNotInvalidException::class.java)
                .hasMessage("invalid mobile phone number")
    }
}