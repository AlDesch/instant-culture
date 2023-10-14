package com.example.instant_culture.ui.compable

import MusicManager.playSoundFail
import MusicManager.playSoundGood
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.instant_culture.R
import com.example.instant_culture.model.Proposal
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
        val heightScreen = LocalConfiguration.current.screenHeightDp
        val weightScreen = LocalConfiguration.current.screenHeightDp
        val context = LocalContext.current
        Dialog(onDismissRequest = { }) {
            if (selectedResponse == questions?.get(questionOrder)?.response) {
                playSoundGood(context)
            } else {
                playSoundFail(context)
            }
            BoxWithConstraints(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .height((heightScreen * 0.9).dp)
                        .fillMaxWidth(),
                    painter = if (selectedResponse == questions?.get(questionOrder)?.response) painterResource(
                        id = R.drawable.goodcard
                    ) else painterResource(id = R.drawable.badcard),
                    contentDescription = null,

                    )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 15.dp, top = 50.dp, start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    val customText =
                        if (selectedResponse == questions?.get(questionOrder)?.response) questions?.get(
                            questionOrder
                        )?.descriptionGood else questions?.get(questionOrder)?.descriptionBad
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((heightScreen * 0.1).dp)
                            .padding(10.dp),
                        text = if (selectedResponse == questions?.get(questionOrder)?.response) "Bien Joué !" else "Raah Dommage !"
                    )
                    if (customText != null) {
                        Text(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth()
                                .height((heightScreen * 0.4).dp),
                            text = customText,
                            fontSize = 20.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((heightScreen * 0.2).dp)
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
        }
    }
}