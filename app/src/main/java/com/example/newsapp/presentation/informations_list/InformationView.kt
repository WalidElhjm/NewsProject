package com.example.newsapp.presentation.informations_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.presentation.util.Util
import com.example.newsapp.presentation.util.Util.getTimeAgo
import com.example.newsapp.R
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun InformationView(
    article: NewsArticle) {
    val uriHandler = LocalUriHandler.current

    val annotatedString = buildAnnotatedString {
        pushStringAnnotation(
            tag = stringResource(R.string.details_tagURL),
            annotation = article.url ?: stringResource(R.string.details_url)
        )
        withStyle(
            style = SpanStyle(
                color = colorResource(id = R.color.purple_500),
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(stringResource(id = R.string.read_article))
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeHolder = painterResource(id = R.drawable.ic_broken_image)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoWithIcon(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                icon = Icons.Default.Edit,
                info = article.author ?: stringResource(id = R.string.not_available),
                testTag = stringResource(R.string.detail_authorInfoIcon)
            )
            InfoWithIcon(
                modifier = Modifier.weight(0.4f),
                icon = Icons.Default.DateRange,
                info = Util.stringToDate(article.publishedAt!!).getTimeAgo(),
                testTag = stringResource(R.string.details_dateInfoIcon)
            )
        }
        Text(
            text = article.title ?: stringResource(id = R.string.not_available),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .testTag(stringResource(R.string.detail_titleTag))
        )
        Text(
            text = article.description ?: stringResource(id = R.string.not_available),
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .testTag(stringResource(R.string.details_descriptionTag))
        )
        ClickableText(
            text = annotatedString,
            modifier = Modifier
                .padding(8.dp)
                .testTag(stringResource(id = R.string.details_CLICKABLE_TEXT)), onClick = {
                annotatedString.getStringAnnotations(it, it).firstOrNull()
                    ?.let { result ->
                        if (result.tag == "URL") {
                            uriHandler.openUri(result.item)
                        }
                    }
            })
    }
}

