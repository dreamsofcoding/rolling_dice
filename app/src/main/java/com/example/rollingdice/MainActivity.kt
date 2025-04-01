package com.example.rollingdice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.rollingdice.ui.theme.RollingDiceTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollingDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RollingScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun RollingScreen(padding: PaddingValues) {

    var diceValue by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DiceRollValueLabel(
            value = diceValue.toString(),
            modifier = Modifier.padding(padding)
        )

        RollButton(onRoll = { diceValue = rollDice() })
    }
}

@Composable
fun RollButton(onRoll: () -> Unit) {
    Button(
        onClick = { onRoll.invoke() },
        modifier = Modifier.wrapContentSize()
    ) {
        Text("Let's Roll")
    }

}

fun rollDice(): Int {
    return Random.nextInt(1, 6)
}

@Composable
fun DiceRollValueLabel(value: String, modifier: Modifier = Modifier) {
    Text(
        text = "You rolled a $value!",
        modifier = modifier,
        fontSize = 30.sp

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RollingDiceTheme {
        DiceRollValueLabel("Android")
    }
}