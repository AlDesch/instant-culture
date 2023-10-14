package com.example.instant_culture.ui.compable

import MusicManager.playSoundFail
import MusicManager.playSoundGood
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.instant_culture.R
import com.example.instant_culture.model.QuizQuestion

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
        val context = LocalContext.current
        Dialog(onDismissRequest = { }) {
            if (selectedResponse == questions?.get(questionOrder)?.response) {
                playSoundGood(context)
            } else {
                playSoundFail(context)
            }
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                Image(
                    painter = if (selectedResponse == questions?.get(questionOrder)?.response) painterResource(
                        id = R.drawable.goodcard
                    ) else painterResource(id = R.drawable.badcard),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 15.dp, top = 50.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    val customText = if (selectedResponse == questions?.get(questionOrder)?.response) questions?.get(questionOrder)?.descriptionGood else questions?.get(questionOrder)?.descriptionBad
                    Text(text = if (selectedResponse == questions?.get(questionOrder)?.response) "Bien Joué !" else "Raaah Dommage !")
                    if (customText != null) {
                        Text(
                            modifier = Modifier.padding(20.dp),
                            text = customText,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Image(
                        modifier = Modifier
                            .width(250.dp)
                            .clickable {
                                if (selectedResponse == questions?.get(questionOrder)?.response) {
                                    onClickNext()
                                } else {
                                    onClickHome()
                                }
                            },
                        painter = if (selectedResponse == questions?.get(questionOrder)?.response)
                            painterResource(id = R.drawable.goodbtn)
                        else
                            painterResource(id = R.drawable.badbtn),
                        contentDescription = null
                    )

                }
            }
            /*
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
                        Text(if (selectedResponse == questions?.get(questionOrder)?.response) "Bonne réponse !" else "Mauvaise réponse !")

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            if (selectedResponse == questions?.get(questionOrder)?.response) {
                                Button(
                                    onClick = {
                                        clickBtn = false
                                        onClickNext()
                                    },
                                    enabled = clickBtn
                                ) {
                                    Text("Yesss")
                                }
                            } else {
                                Button(
                                    onClick = {
                                        clickBtn = false
                                        onClickHome()
                                    },
                                    enabled = clickBtn
                                ) {
                                    Text("Vraiment ? ! ?")
                                }
                            }
                        }
                    }
                }
            }*/

        }
    }
}