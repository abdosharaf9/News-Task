package com.abdosharaf.newstask.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    NewsTaskTheme {
        Column {
            CategoryItem(
                category = "Health",
                isSelected = false,
                onClick = {}
            )

            CategoryItem(
                category = "Health",
                isSelected = true,
                onClick = {}
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(horizontal = 16.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}