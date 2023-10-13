package com.example.instant_culture.ui.compable

import RotatingScaledBackgroundImage
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
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
    rotataingSpeed: Int = 30000
) {
    val questionTitle: String? = questions?.get(questionOrder)?.question
    val selectedResponse =
        remember { mutableStateOf<Int?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    MusicManager.playMusic(
        context,
        when(difficulty) {
            0 -> R.raw.eazy
            1 -> R.raw.hard
            2 -> R.raw.impossible
            else -> R.raw.main
        }
    )

    RotatingScaledBackgroundImage(painter = painterResource(id = background), durationMillis = rotataingSpeed)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
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
            IconButton(
                onClick = {
                    print("Menu")
                }
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Menu,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Surface(
                color = Color.White,
                border = BorderStroke(5.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (questionTitle != null) {
                        Text(
                            text = questionTitle, //limiter a 120 char
                            style = MaterialTheme.typography.bodyLarge,
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

        Spacer(modifier = Modifier.height(50.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        selectedResponse.value == responseOrder && !isCorrectResponse -> R.drawable.selectedbadresponse
                        selectedResponse.value != responseOrder && isCorrectResponse -> R.drawable.goodresponse
                        selectedResponse.value != responseOrder -> R.drawable.badresponse
                        else -> R.drawable.btnresponse
                    }

                    Box(
                        modifier = Modifier
                            .width(350.dp)
                            .height(95.dp)
                    ) {
                        Image(
                            modifier = Modifier
                                .width(350.dp)
                                .height(80.dp)
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
                                .padding(start = 70.dp, end = 20.dp)
                                .height(70.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = responseText, // limite de 80 char
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
                onClickNext = onClickNext,
                onClickHome = onClickHome,
                selectedResponse = selectedResponse.value,
                questionOrder = questionOrder,
                questions = questions
            )
        }
    }
}


@Composable
fun CustomPopup(
    showDialog: Boolean,
    onClickNext: () -> Unit,
    onClickHome: () -> Unit,
    selectedResponse: Int?,
    questionOrder: Int,
    questions: List<QuizQuestion>?
) {
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(if (selectedResponse == questions?.get(questionOrder)?.response) "Bravo !" else "Raté :/")

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            if (selectedResponse == questions?.get(questionOrder)?.response) {
                                Button(
                                    onClick = {
                                        onClickNext()
                                    }
                                ) {
                                    Text("Yesss")
                                }
                            } else {
                                Button(
                                    onClick = {
                                        onClickHome()
                                    }
                                ) {
                                    Text("Vraiment ? ! ?")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


