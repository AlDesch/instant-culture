package com.example.instant_culture.ui.compable

import PlayMusic
import RotatingScaledBackgroundImage
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instant_culture.R

@Composable
fun BeginView(
    onClickPlay: () -> Unit,
) {
    val context = LocalContext.current
    MusicManager.playMusic(context,R.raw.main)

    RotatingScaledBackgroundImage(painter = painterResource(id = R.drawable.bwbackground), durationMillis = 30000)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth(),
                onClick = onClickPlay
            ) {
                Image(
                    painter = painterResource(id = R.drawable.playbtn),
                    contentDescription = null,
                    modifier = Modifier
                        .height(210.dp)
                        .fillMaxWidth()
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            val context = LocalContext.current
            IconButton(
                modifier = Modifier
                    .height(210.dp)
                    .fillMaxWidth(),
                onClick = {
                    val activity = context as? ComponentActivity
                    activity?.finish()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.quittebtn),
                    contentDescription = null,
                    modifier = Modifier
                        .height(210.dp)
                        .fillMaxWidth()
                )
            }
        }
        Text(text = "Instant Culture® V1.0.0 2023",
            fontSize = 10.sp,
            fontWeight = FontWeight.W700
        )
    }
}
