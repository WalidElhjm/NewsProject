package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.informations_list.InformationScreen
import com.example.newsapp.presentation.news_list.NewsListScreen
import com.example.newsapp.presentation.news_list.NewsListViewModel
import com.example.newsapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Surface(color = Color.White) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            val viewModel: NewsListViewModel = hiltViewModel()
                            NewsListScreen(
                                viewModel = viewModel,
                                navController
                            )
                        }
                        composable(
                            route = Screen.DetailScreen.route,
                            arguments = listOf(navArgument(Screen.DetailScreen.NAV_ARG_KEY) {
                                type = NavType.IntType
                            })
                        ) {
                            val viewModel: NewsListViewModel = hiltViewModel()
                            val index = it.arguments?.getInt(Screen.DetailScreen.NAV_ARG_KEY)

                            InformationScreen( onBackPressed = { navController.popBackStack() },
                                viewModel,
                                index = index)
                        }
                    }
                }
            }
        }
    }
}
