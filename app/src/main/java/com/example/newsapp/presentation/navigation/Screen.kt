package com.example.newsapp.presentation.navigation

sealed class Screen(val route: String) {
    object NewsListScreen: Screen("news_list_screen")
    object DetailScreen : Screen("information_screen/{index}") {
        const val NAV_ARG_KEY = "index"
        fun passNewsIndex(index: Int): String {
            return "information_screen/${index}"
        }
    }
}