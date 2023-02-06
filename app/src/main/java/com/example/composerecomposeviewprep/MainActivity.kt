package com.example.composerecomposeviewprep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composerecomposeviewprep.ui.theme.ComposeRecomposeViewPrepTheme
import kotlin.random.Random

// check how often do parts of compose UI recompose in Layout Inspector
// Look under Recomposition Counts - Red color shows recomposition occurrences
//      - first number means recomposed
//      - second number means skipped recomposition, because there was no reason to recompose
// Button will recompose many times because it has a ripple animation.

// Debugging basic code in the Debugger (debug mode and break points)
// controls explained
// Step Over - debugger moves to the next line and executes, not jumping into the scope of function
//              - it will go into the scope of a function when a break point is there
// Step Into - step into the scope of function that is being executed
// Force Step Into - steps into functions of libraries (not my code)
// Step Out - steps out of a function (when do not need to observe it), but finishes executing the function
// Drop Frame - drops all the variables that a function created and moves back before functions execution
// Run to Cursor - moves to where the cursor is blinking

// Watches - drag and drop selected variables of statements to see their value

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecomposeViewPrepTheme {
                var color1 by remember {
                    mutableStateOf(Color.White)
                }
                val color1Animated by animateColorAsState(
                    targetValue = color1,
                    animationSpec = tween(durationMillis = 1000)
                )

                var color2 by remember {
                    mutableStateOf(Color.White)
                }
                var color3 by remember {
                    mutableStateOf(Color.White)
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color1Animated)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color2)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(color3)
                    )
                    Button(onClick = {
                        when(Random.nextInt(3)) {
                            0 -> color1 = Color.random()
                            1 -> color2 = Color.random()
                            2 -> color3 = Color.random()
                        }
                    }) {
                        Text(text = "Change random color")
                    }
                }
            }
        }
    }
}

fun Color.Companion.random(): Color {
    return Color(Random.nextLong(0xFFFFFFFF))
}