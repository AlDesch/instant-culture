package com.example.instant_culture.ui.compable

import RotatingScaledBackgroundImage
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instant_culture.R

@Composable
fun ConfirmationView(
    onClickReday: () -> Unit,
    onClickHome: () -> Unit
) {
    val heightScreen = LocalConfiguration.current.screenHeightDp

    RotatingScaledBackgroundImage(
        painter = painterResource(id = R.drawable.confirmationmode),
        durationMillis = 30000
    )
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height((heightScreen * 0.6).dp)
            .fillMaxWidth()
    ) {
        Image(
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height((heightScreen * 0.4).dp)
        )
        IconButton(
            modifier = Modifier
                .height((heightScreen * 0.2).dp)
                .fillMaxWidth(),
            onClick = onClickReday
        ) {
            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.readybtn),
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
            onClick = onClickHome
        ) {
            Image(
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.notyet),
                contentDescription = null,
                modifier = Modifier
                    .height((heightScreen * 0.2).dp)
                    .fillMaxWidth()
            )
        }
    }
}