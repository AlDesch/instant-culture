import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instant_culture.R
import com.example.instant_culture.model.Screens
import kotlinx.coroutines.delay

@Composable
fun WaitingScreen(navController: NavController) {
    var timeLeft by remember { mutableIntStateOf(3) }

    RotatingScaledBackgroundImage(
        painter = painterResource(id = R.drawable.waitingmode),
        durationMillis = 1000
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .align(CenterVertically)
                    .clip(CircleShape)
                    .border(7.dp, MaterialTheme.colorScheme.primaryContainer, CircleShape)
            ) {
                Text(
                    timeLeft.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
    LaunchedEffect(key1 = "timer") {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft -= 1
        }
        navController.navigate(route = Screens.Question.name)
    }
}