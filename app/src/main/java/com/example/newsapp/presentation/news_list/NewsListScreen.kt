package com.example.newsapp.presentation.news_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.common.toError
import com.example.newsapp.presentation.component.ErrorScreen
import com.example.newsapp.presentation.component.LoadingScreen
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.theme.Purple500
import com.example.newsapp.presentation.theme.Purple700

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    navController: NavController
    ) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.articles) { article ->
                if (state.articles.indexOf(article) == 0) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Purple500),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text =  stringResource(id = R.string.app_name),
                            style = MaterialTheme.typography.h4,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                NewsListItem(
                    article = article,
                    onItemClick = {
                        navController.navigate(Screen.DetailScreen.passNewsIndex(state.articles.indexOf(article)))
                    //onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
                        }
                )
            }
        }
        if (state.isLoading) {
            LoadingScreen()
        }

        state.error.let {
            if (it != null) {
                ErrorScreen(error = it.toError())
            }
        }
    }

}