package com.example.newsapp.presentation.news_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.example.newsapp.R
import com.example.newsapp.data.model.NewsArticle
import com.example.newsapp.presentation.util.Util
import com.example.newsapp.presentation.util.Util.getTimeAgo

@Composable
fun NewsListItem(article: NewsArticle, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onItemClick()
            }
            .semantics {
                contentDescription = "NewItem"
            }
    ) {
        GlideImage(
            imageModel = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            error = painterResource(id = R.drawable.ic_broken_image),
            placeHolder = painterResource(id = R.drawable.ic_broken_image)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 6.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            article.publishedAt?.let {
                Text(
                    text = Util.stringToDate(article.publishedAt).getTimeAgo(),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.semantics {
                        contentDescription = "PublishedNew"
                    }
                )
            }
            Text(
                text = article.title ?: String(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.semantics {
                    contentDescription = "TitleNew"
                }
            )
        }
    }
}

