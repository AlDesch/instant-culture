package com.example.instant_culture.ui.compable

import RotatingScaledBackgroundImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.instant_culture.R
import com.example.instant_culture.model.QuizQuestion

@Composable
fun QuestionView(
    onClick: () -> Unit,
    questions: List<QuizQuestion>?,
    questionOrder: Int,
    background: Int,
    onClickNext: () -> Unit,
    onClickHome: () -> Unit,
    difficulty: Int,
    rotatingSpeed: Int = 30000
) {
    val questionTitle: String? = questions?.get(questionOrder)?.question
    val selectedResponse =
        remember { mutableStateOf<Int?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    val heightScreen = LocalConfiguration.current.screenHeightDp
    val weightScreen = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current

    MusicManager.playMusic(
        context,
        when (difficulty) {
            0 -> R.raw.eazy
            1 -> R.raw.hard
            2 -> R.raw.impossible
            else -> R.raw.main
        }
    )

    RotatingScaledBackgroundImage(
        painter = painterResource(id = background),
        durationMillis = rotatingSpeed
    )
    ResponsiveBox {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth().height((heightScreen *0.1).dp)
            ) {
                IconButton(
                    onClick = onClick
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
                Image(
                    modifier = Modifier.width(250.dp),
                    painter = painterResource(id = R.drawable.instant_culture),
                    contentDescription = null
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth().height((heightScreen*0.3).dp)
            ) {
                Surface(
                    color = Color.White,
                    border = BorderStroke(5.dp, Color.Black),
                    shape = RoundedCornerShape (16.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .aspectRatio(1.3f)
                        .padding(bottom = 20.dp, top = 20.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (questionTitle != null) {
                            Text(
                                text = questionTitle, //limiter a 120 char
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(
                                    bottom = 15.dp,
                                    top = 10.dp,
                                    start = 15.dp,
                                    end = 15.dp
                                )
                            )
                        }
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().height((heightScreen*0.5).dp)
            ) {
                LaunchedEffect(key1 = questionOrder) {
                    selectedResponse.value = null
                }
                questions?.get(questionOrder)?.proposal?.let { proposal ->
                    listOf(
                        proposal.p1,
                        proposal.p2,
                        proposal.p3,
                        proposal.p4
                    ).forEachIndexed { index, responseText ->
                        val responseOrder = index + 1
                        val isCorrectResponse = responseOrder == questions[questionOrder].response

                        val drawableInt = when {
                            selectedResponse.value == null -> R.drawable.btnresponse
                            selectedResponse.value == responseOrder && isCorrectResponse -> R.drawable.goodresponse
                            selectedResponse.value == responseOrder && !isCorrectResponse -> R.drawable.badresponse
                            else -> R.drawable.btnresponse
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((heightScreen*0.13).dp)
                        ) {
                            Image(
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((heightScreen*0.1).dp)
                                    .clickable {
                                        selectedResponse.value = responseOrder
                                        showDialog.value = true
                                    },
                                painter = painterResource(id = drawableInt),
                                contentDescription = null,
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height((heightScreen*0.1).dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = responseText, // limit de 80 char
                                    fontSize = 20.sp,
                                    style = TextStyle(lineHeight = 22.sp)
                                )
                            }
                        }
                    }
                }
            }
            if (showDialog.value) {
                CustomPopup(
                    showDialog = showDialog.value,
                    onClickNext = {
                        showDialog.value = false
                        onClickNext()
                    },
                    onClickHome = {
                        showDialog.value = false
                        onClickHome()
                    },
                    selectedResponse = selectedResponse.value,
                    questionOrder = questionOrder,
                    questions = questions
                )
            }
        }
    }
}

@Composable
fun ResponsiveBox(content: @Composable BoxScope.(Constraints) -> Unit) {
    BoxWithConstraints {
        content(constraints)
    }
}
