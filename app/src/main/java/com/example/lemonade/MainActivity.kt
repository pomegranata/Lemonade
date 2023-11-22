package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}


@Composable
fun LemonadeProperties(
    painterID: Int,
    contentDesc: Int,
    textValue: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults
                    .buttonColors(containerColor = Color(red = 195, green = 236, blue = 210))
            ) {
                Image(
                    painter = painterResource(id = painterID),
                    contentDescription = stringResource(id = contentDesc)
                )
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = stringResource(id = textValue),
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun Lemonade() {

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    var stepNow by remember {
        mutableStateOf(1)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (stepNow) {
            1 -> {
                LemonadeProperties(
                    painterID = R.drawable.lemon_tree,
                    contentDesc = R.string.tree,
                    textValue = R.string.tap,
                    onImageClick = {
                        stepNow = 2
                        squeezeCount = (2..4).random()
                    }
                )
            }
            2 -> {
                LemonadeProperties(
                    painterID = R.drawable.lemon_squeeze,
                    contentDesc = R.string.lemon,
                    textValue = R.string.squeeze,
                    onImageClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            stepNow = 3
                        }
                    })
            }
            3 -> {
                LemonadeProperties(painterID = R.drawable.lemon_drink,
                    contentDesc = R.string.glass,
                    textValue = R.string.drink,
                    onImageClick = {
                        stepNow = 4
                    }
                )
            }
            4 -> {
                LemonadeProperties(painterID = R.drawable.lemon_restart,
                    contentDesc = R.string.empty_glass,
                    textValue = R.string.empty,
                    onImageClick = {
                        stepNow = 1
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        Lemonade()
    }
}