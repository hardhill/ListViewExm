package ru.hardhill.listviewexm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import ru.hardhill.listviewexm.response.SampleData
import ru.hardhill.listviewexm.ui.theme.ListViewExmTheme
import ru.hardhill.listviewexm.view.SampleDataDetails
import ru.hardhill.listviewexm.view.SampleList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListViewExmTheme {
                navigatePage()
            }
        }
    }
}

@Composable
fun navigatePage() {
    var navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = "sample_data")
    {
        composable("sample_data") {
            SampleList(navController = navHostController)
        }
        composable("sample_detail/{item}", arguments = listOf(navArgument(name = "item") {
            type = NavType.StringType
        })) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("item")
                .let { json ->
                    val item = Gson().fromJson(json, SampleData::class.java)
                    SampleDataDetails(data = item)
                }
        }
    }
}
