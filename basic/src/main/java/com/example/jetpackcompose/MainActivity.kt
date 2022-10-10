package com.example.jetpackcompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basics.ui.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DefaultPreview()
        }
    }
}


@Composable
fun CardContent(name: String) {

    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
//                .padding(24.dp)
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
//                    .animateContentSize( // 텍스트가 뷰가 잘려보이는 현상
//                        animationSpec = spring(
//                            dampingRatio = Spring.DampingRatioMediumBouncy,
//                            stiffness = Spring.StiffnessLow
//                        )
//                    )
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
//                        modifier = Modifier.padding(top = 10.dp)
                )
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }

    }
}

@Composable
fun MyApp(names: List<String> = listOf("World", "Compose")) {
//    val showOnBoarding = remember { mutableStateOf(true) }
    val showOnBoarding = rememberSaveable { mutableStateOf(true) }
    if (showOnBoarding.value) {
        OnBoardingScreen {
            showOnBoarding.value = !showOnBoarding.value
        }
    } else {
        Greeting()
    }
}


@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome Basic CodeLab!")
            Button(onClick = {
                onContinueClicked()
            }) {
                Text(text = "Continue")
            }
        }
    }
}


//@Preview(showBackground = true, widthDp = 320, heightDp = 400)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme() {
        MyApp()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 500)
@Composable
fun Greeting() {
    BasicsCodelabTheme(){
        val names: List<String> = List(1000) {
            "$it"
        }
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Card(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    CardContent(name)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
        OnBoardingScreen() {

        }
}