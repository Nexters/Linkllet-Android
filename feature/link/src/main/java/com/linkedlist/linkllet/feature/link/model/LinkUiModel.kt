package com.linkedlist.linkllet.feature.link.model

import android.annotation.SuppressLint
import com.linkedlist.linkllet.core.data.model.Link
import java.text.SimpleDateFormat
import java.util.TimeZone

data class LinkUiModel(
    val id : Long,
    val title : String,
    val link : String,
    val date : String
)

fun Link.toUiModel() : LinkUiModel =
    LinkUiModel(
        id = id,
        title = name,
        link = url,
        date = formatDateString(createAt)
    )

@SuppressLint("SimpleDateFormat")
fun formatDateString(input: String): String { /// 수정 필요.
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = dateFormat.parse(input)

        // 원하는 출력 형식으로 포맷팅할 SimpleDateFormat을 생성합니다.
        val outputFormat = SimpleDateFormat("yyyy.MM.dd")

        // Date 객체를 원하는 형식으로 포맷팅합니다.
        outputFormat.format(date)
    }catch (e: Exception){
        ""
    }
}