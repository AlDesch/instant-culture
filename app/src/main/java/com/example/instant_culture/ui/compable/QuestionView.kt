package com.example.instant_culture.ui.compable

import RotatingScaledBackgroundImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instant_culture.R
import com.example.instant_culture.model.ResponseCard
import com.example.instant_culture.ui.theme.InstantcultureTheme

@Composable
fun QuestionView(onClick: () -> Unit) {
    RotatingScaledBackgroundImage(painter = painterResource(id = R.drawable.vertvertbackground))
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
                    Text(
                        text = "C'est une grande question, m, donc vfeokez foefj jfàizejf ezoj-père que tu as lakyuky ukuykuyuk yuk uk  répo efz fnse?fe", //limiter a 120 char
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 15.dp, top = 10.dp, start = 15.dp, end = 15.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var i = 0
            while (i < 4) {
                i++
                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .height(95.dp)
                        .clickable {
                            print(i.toString())
                        },
                ) {
                    var drawableInt = R.drawable.btnresponse
                    when (i) {
                        1 -> {
                            drawableInt = ResponseCard.A.draw
                        }
                        2 -> {
                            drawableInt = ResponseCard.B.draw
                        }
                        3 -> {
                            drawableInt = ResponseCard.C.draw
                        }
                        4 -> {
                            drawableInt = ResponseCard.D.draw
                        }
                    }
                    Image(
                        modifier = Modifier
                            .width(350.dp)
                            .height(80.dp),
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
                            text = "iheuHEUfhçuefHIEfhoiefjoipefhpKIPOefjopiefiojfOEJFPOjfeopîhjfe eff ef efe FYEJSDI", // limite de 80 char
                            fontSize = 20.sp,
                            style = TextStyle(lineHeight = 22.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstantcultureTheme {
        QuestionView(onClick = {})
    }
}