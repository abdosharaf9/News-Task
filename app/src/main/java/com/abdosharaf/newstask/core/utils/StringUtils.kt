package com.abdosharaf.newstask.core.utils

import android.content.Context
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.abdosharaf.newstask.R
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun getLoremString(words: Int) =
    LoremIpsum(words = words).values.iterator().asSequence().joinToString(" ")

fun stringToDate(date: String, pattern: String, context: Context): String {
    return try {
        val parsedDate = OffsetDateTime.parse(date).toLocalDate()
        val today = LocalDate.now()
        val yesterday = today.minusDays(1)

        when (parsedDate) {
            today -> context.getString(R.string.today)
            yesterday -> context.getString(R.string.yesterday)
            else -> parsedDate.format(DateTimeFormatter.ofPattern(pattern))
        }
    } catch (t: Throwable) {
        ""
    }
}