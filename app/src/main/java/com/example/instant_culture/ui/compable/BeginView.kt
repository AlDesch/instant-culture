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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instant_culture.R

@Composable
fun BeginView(
    onClickPlay: () -> Unit,
) {
    val heightScreen = LocalConfiguration.current.screenHeightDp
    val weightScreen = LocalConfiguration.current.screenHeightDp
    val context = LocalContext.current

    MusicManager.playMusic(context, R.raw.main)

    RotatingScaledBackgroundImage(
        painter = painterResource(id = R.drawable.menumode),
        durationMillis = 30000
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height((heightScreen * 0.4).dp)
        )
        //Spacer(modifier = Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height((heightScreen * 0.6).dp)
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .height((heightScreen * 0.2).dp)
                    .fillMaxWidth(),
                onClick = onClickPlay
            ) {
                Image(
                    contentScale = ContentScale.FillHeight,
                    painter = painterResource(id = R.drawable.playbtn),
                    contentDescription = null,
                    modifier = Modifier
                        .height((heightScreen * 0.2).dp)
                        .fillMaxWidth()
                )
            }
            IconButton(
                modifier = Modifier
                    .height((heightScreen * 0.2).dp)
                    .fillMaxWidth(),
                onClick = {
                    val activity = context as? ComponentActivity
                    activity?.finish()
                }
            ) {
                Image(
                    contentScale = ContentScale.FillHeight,
                    painter = painterResource(id = R.drawable.quittebtn),
                    contentDescription = null,
                    modifier = Modifier
                        .height((heightScreen * 0.2).dp)
                        .fillMaxWidth()
                )
            }
            Text(
                text = "Instant Culture® V1.0.0 2023",
                fontSize = 10.sp,
                fontWeight = FontWeight.W700
            )
        }

    }
}