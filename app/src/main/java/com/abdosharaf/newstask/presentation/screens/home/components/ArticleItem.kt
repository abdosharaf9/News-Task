package com.abdosharaf.newstask.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdosharaf.newstask.R
import com.abdosharaf.newstask.core.utils.getLoremString
import com.abdosharaf.newstask.core.utils.stringToDate
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.components.MainImage
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview
@Composable
private fun ArticleItemPreview() {
    NewsTaskTheme {
        ArticleItem(
            article = DomainArticle(
                author = "",
                content = "",
                description = "",
                publishedAt = "2022-04-21T09:53:00Z",
                source = "BBC",
                title = getLoremString(10),
                url = "",
                urlToImage = ""
            ),
            onClick = {}
        )
    }
}

@Composable
fun ArticleItem(
    article: DomainArticle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainImage(
            data = article.urlToImage,
            errorImage = R.drawable.image_error_square,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_browser),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp),
                    )

                    Text(
                        text = article.source,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = stringResource(id = R.string.published_on),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp),
                    )

                    Text(
                        text = stringToDate(
                            date = article.publishedAt,
                            pattern = "MMM dd',' yyyy",
                            context = context
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}