package com.kancho.learning

import org.assertj.core.api.Assertions
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Test

class JsoupeTests {

    @Test
    fun URI_요청후_도큐먼트_확인() {
        val doc: Document =
                Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&query=%EB%AC%BC%EA%B3%A0%EA%B8%B0%EC%9E%90%EB%A6%AC%20%EC%9A%B4%EC%84%B8").get()

        val result: String = doc.select("*#yearFortune > div > div.detail.detail2 > p:nth-child(4)").text()

        Assertions.assertThat(result).isNotEmpty()
    }
}