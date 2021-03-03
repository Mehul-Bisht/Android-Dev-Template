/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.*

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel("MainVM")

            NavHost(
                navController = navController,
                startDestination = "puppyList",
                builder = {
                    composable(
                        route = Screen.PuppyListScreen.route
                    ) { navBackStackEntry ->

                        PuppyListScreen(
                            navFunction = {
                                navController.navigate(it)
                            },
                            list = viewModel.puppyList.value,
                            viewModel = viewModel
                        )
                    }

                    composable(
                        route = Screen.PuppyDetailScreen.route + "/{brawlerId}",
                        arguments = listOf(navArgument("brawlerId"){
                            type = NavType.IntType
                        })
                    ) { navBackStackEntry ->
                        PuppyDetailScreen(
                            puppyId = navBackStackEntry.arguments?.getInt("brawlerId"),
                            viewModel = viewModel
                        )
                    }
                }
            )

        }
    }
}
