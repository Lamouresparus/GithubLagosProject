package com.githubhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.githubhub.ui.App
import com.githubhub.ui.PrimaryText
import com.githubhub.ui.theme.GithubHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            GithubHubTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            PrimaryText(text = stringResource(id = R.string.app_name))
                        },
                        backgroundColor = Color.Transparent,
                        contentColor = Color.Gray,
                        elevation = 2.dp
                    )
                }) {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        App()
                    }
                }
            }
        }
    }
}
