package com.abdosharaf.newstask.core.utils

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

fun getLoremString(words: Int) =
    LoremIpsum(words = words).values.iterator().asSequence().joinToString(" ")