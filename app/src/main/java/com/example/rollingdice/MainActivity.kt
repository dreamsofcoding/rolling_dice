package com.example.rollingdice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        DiceImage(diceValue)

        Spacer(Modifier.height(30.dp))

        DiceRollValueLabel(diceValue.toString())

        Spacer(Modifier.height(30.dp))

        RollButton(onRoll = { diceValue = rollDice() })
    }
}

@Composable
fun DiceImage(diceValue: Int) {
    val imageResource = when(diceValue) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = diceValue.toString()

    )
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
    return Random.nextInt(1, 7)
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
fun RollingScreenPreview() {
    RollingDiceTheme {
        RollingScreen(padding = PaddingValues(0.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DiceRollValueLabelPreview() {
    RollingDiceTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DiceRollValueLabel("6")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RollButtonPreview() {
    RollingDiceTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RollButton(onRoll = {})
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DiceImagePreview() {
    RollingDiceTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DiceImage(diceValue = 3)
        }
    }
}
