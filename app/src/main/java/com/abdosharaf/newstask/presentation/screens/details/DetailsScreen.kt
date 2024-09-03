package com.abdosharaf.newstask.presentation.screens.details

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import com.abdosharaf.newstask.R
import com.abdosharaf.newstask.core.utils.stringToDate
import com.abdosharaf.newstask.domain.model.DomainArticle
import com.abdosharaf.newstask.presentation.components.MainImage
import com.abdosharaf.newstask.presentation.theme.NewsTaskTheme

@Preview
@Composable
private fun DetailsScreenPreview() {
    NewsTaskTheme {
        DetailsScreen(
            article = DomainArticle(
                author = "WMTW Portland",
                content = "WALDO COUNTY, Maine —A Waldo County resident has died after being infected by a rare virus spread by infected ticks, the Maine Center for Disease Control and Prevention confirmed.\\r\\nOfficials say the … [+1136 chars]",
                description = "Powassan virus is a rare illness spread by an infected woodchuck or deer tick bite.",
                publishedAt = "2022-04-21T09:53:00Z",
                source = "BBC",
                title = "Mainer dies following rare virus spread by infected tick bite, officials confirm - WMTW Portland",
                url = "",
                urlToImage = ""
            ),
            category = "Health",
            navigateBack = {}
        )
    }
}

@Composable
fun DetailsScreen(
    article: DomainArticle,
    category: String,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            DetailsTopBar(url = article.url, navigateBack = navigateBack)
        },
        modifier = Modifier.safeContentPadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            ContentSection(article = article, category = category)

            BrowserButton(url = article.url)
        }
    }
}

@Composable
fun DetailsTopBar(
    url: String,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = navigateBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.go_back),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        IconButton(
            onClick = {
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/*"
                    putExtra(Intent.EXTRA_TEXT, url)
                    context.startActivity(this)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = stringResource(id = R.string.share),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun ColumnScope.ContentSection(
    article: DomainArticle,
    category: String
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "${
                stringToDate(
                    date = article.publishedAt,
                    pattern = "EEEE dd MMM yyyy",
                    context = context
                )
            } • $category • ${article.source}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        MainImage(
            data = article.urlToImage,
            placeholderImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.ic_launcher_background,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        if (article.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            SelectionContainer {
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        if (article.content.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            val spanned =
                HtmlCompat.fromHtml(article.content, HtmlCompat.FROM_HTML_MODE_COMPACT)
            val annotatedString = buildAnnotatedString {
                append(spanned.toString())
            }

            SelectionContainer {
                Text(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            if (article.author.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Written by",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(16.dp)
                    )

                    Text(
                        text = article.author,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BrowserButton(
    url: String
) {
    val context = LocalContext.current

    Button(
        onClick = {
            Intent(Intent.ACTION_VIEW).apply {
                data = url.toUri()
                context.startActivity(this)
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_browser),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = stringResource(id = R.string.open_in_browser),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
