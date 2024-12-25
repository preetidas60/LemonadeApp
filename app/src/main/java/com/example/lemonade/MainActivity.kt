package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
//                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
//                    LemonadeScreen()
//                }
                LemonScreenApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonScreenApp() {
    var currentScreen by rememberSaveable { mutableStateOf(1) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.Yellow)
                )
            )
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            when (currentScreen) {
                1 -> {
                    LemonadeScreen(image = R.drawable.lemon_tree,
                        text = stringResource(R.string.text1),
                        onClick = { currentScreen = 2 })
                }

                2 -> LemonadeScreen(image = R.drawable.lemon_squeeze,
                    text = stringResource(R.string.text2),
                    onClick = { currentScreen = 3 })

                3 -> LemonadeScreen(image = R.drawable.lemon_drink,
                    text = stringResource(R.string.text3),
                    onClick = { currentScreen = 4 })

                4 -> LemonadeScreen(image = R.drawable.lemon_restart,
                    text = stringResource(R.string.text4),
                    onClick = { currentScreen = 1 })
            }
        }

    }
}

@Composable
fun LemonadeScreen(image : Int,
                   text : String,
                   onClick : () -> Unit,
                   modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(image),
            contentDescription = "Lemon Tree",
            Modifier
                .clickable(onClick = onClick)
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.lightgreen))
                .padding(30.dp)


            )
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonScreenApp()
    }
}