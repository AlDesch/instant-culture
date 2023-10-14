import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.instant_culture.R
import kotlinx.coroutines.delay


@SuppressLint("UnrememberedMutableState")
@Composable
fun CountdownTimerCircle(totalTimeMillis: Int = 3000, onTimeFinish: () -> Unit) {
    var timeLeftMillis by remember { mutableIntStateOf(totalTimeMillis) }

    val sweepAngle = (timeLeftMillis / totalTimeMillis.toFloat()) * 360f

    LaunchedEffect(key1 = timeLeftMillis) {
        if (timeLeftMillis > 0) {
            delay(50)
            timeLeftMillis -= 60
        } else {
            onTimeFinish()
        }
    }
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
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(CenterVertically)
                    .clip(CircleShape)
            ) {
                Canvas(
                    modifier = Modifier
                ) {
                    drawArc(
                        color = Color.Black,
                        startAngle = -90f,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        style = Stroke(width = 100.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
            }
        }
    }
}

