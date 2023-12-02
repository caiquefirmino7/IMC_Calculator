package com.example.imccalc

import DARK_BLUE
import IMCCalculatorComposeTheme
import LIGHT_BLUE
import WHITE
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imccalc.calculation.IMCCalculation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCCalculatorComposeTheme {
                IMCCalculator()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IMCCalculator() {

    val context = LocalContext.current
    val imcCalculation = IMCCalculation()

    var weight by remember {
    mutableStateOf("")
    }

    var height by remember {
        mutableStateOf("")
    }

    var resultText by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGHT_BLUE,
                title = {
                    Text(
                        text = "Calculadora de IMC",
                        color = WHITE
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            weight = ""
                            height = ""
                            resultText = ""
                        }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon_refresh),
                            contentDescription = "icon to reset all fields"
                        )
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calculadora de IMC",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_BLUE,
                modifier = Modifier
                    .padding(50.dp)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = {
                    if (it.length <= 6) {
                        weight = it
                    }
                },
                label = {
                    Text(
                        text = "peso (kg)"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(
                    DARK_BLUE,
                    18.sp,
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = height,
                onValueChange = {
                    if(it.length <= 4)
                    height = it
                },
                label = {
                    Text(
                        text = "altura"
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_BLUE,
                    focusedBorderColor = LIGHT_BLUE,
                    textColor = DARK_BLUE,
                    focusedLabelColor = DARK_BLUE
                ),
                textStyle = TextStyle(
                    DARK_BLUE,
                    18.sp
                ),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(
                onClick = {
                    if(weight.isEmpty() || height.isEmpty()){
                        Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                    } else  {
                        imcCalculation.IMCCalculate(weight, height)
                        resultText = imcCalculation.IMCResult
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LIGHT_BLUE,
                    contentColor = WHITE
                )
            ) {
                Text(
                    text ="Calcular IMC",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = resultText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DARK_BLUE

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        IMCCalculator()

}
