package io.github.uxlabspk.neuroscape.views.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.R


@Composable
fun InfoHome (
    username:String,
    date: String,
) {

    Column(
        modifier = Modifier

    ) {
        Text(
            text = "Hello $username!",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight(600)

        )
        Text(
            text = "Last Scan on $date ",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun HomeUserInfo(
    @DrawableRes drawable: Int ,
    username: String,
    date: String,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier,
        color = Color(0xFFF7F7F7)
    ) {
        Row(
            modifier = Modifier
                .width(354.dp)
                .height(112.dp)
                .padding(horizontal = 16.dp)                ,
            verticalAlignment = Alignment.CenterVertically,


            ) {
            Column(modifier = Modifier
            ) {
                Image(
                    painterResource(drawable),
                    contentDescription = null,
                    modifier = Modifier.size(height = 65.dp, width = 79.dp)
                )
            }
            Column(
                modifier = Modifier
            ) {
                InfoHome(username, date)
            }
        }
    }

}

@Composable
fun TestingInfo(
    @StringRes testing: Int,
    @StringRes score: Int,
    number: Int,
) {
    Column (modifier = Modifier){
        Text(
            stringResource(testing),
            fontWeight = FontWeight(500),
            fontSize = 20.sp
        )
        Text(
            stringResource(score)+ "   " + number + "/10",
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        )
    }
}
@Composable
fun ASD(
) {
    Surface(
        modifier = Modifier
            .height(32.dp)
            .width(57.dp)
        ,
        shape = MaterialTheme.shapes.small,
        color = Color.Red,
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "ASD",
                color = Color.White,
            )
        }
    }
}


@Composable
fun Tests(
    testing: Int,
    score: Int,
    number: Int,
    modifier: Modifier
) {
    Surface(
        modifier = Modifier
            .width(354.dp)
            .height(72.dp),
        shape = MaterialTheme.shapes.medium,
        color = Color(0xFFF7F7F7)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column(

            ) {
                TestingInfo(
                    testing,
                    score,
                    number
                )
            }
            Column(

            ) {
                ASD()
            }
        }
    }
}

@Composable
fun ButtonScan(
    buttonText: String,
    btnColors: ButtonColors,
    textColor: Color
) {
    Button(onClick = { /*TODO*/ }, colors = btnColors, shape = MaterialTheme.shapes.small,) {
        Text(text = buttonText, fontSize = 16.sp, color = textColor)

    }
}


@Composable
fun ScanButtons() {
    Surface (
    ) {
        Row(
            modifier = Modifier
                .width(354.dp)
                .height(44.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            Column {
                ButtonScan(
                    btnColors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF7F7F7)),
                    buttonText = "View All Scan",
                    textColor = Color.Black
                )
            }
            Column {

                ButtonScan(
                    buttonText = "New Scan",
                    btnColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0080FF)),
                    textColor = Color.White
                )
            }
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeUserInfoPreview( ) {

        HomeUserInfo(R.drawable.ic_logo,"Hamza Waheed", "April 11, 2024")


}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun TestsPreview () {
    Tests(modifier = Modifier,testing = R.string.testing, score = R.string.Score, number = 1)
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BtnPre() {

        ScanButtons()

}

@Composable
fun Home() {

        Column (

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Spacer(modifier = Modifier.height(140.dp). padding(top = 24.dp))
                HomeUserInfo(R.drawable.ic_sign_in_flow,"Hamza Waheed", "April 11, 2024")
            }
            Row {
                Spacer(modifier = Modifier.height(70.dp))
                ScanButtons()

            }
            Row {
                Spacer(modifier = Modifier.height(90.dp))

                Tests(modifier = Modifier,testing = R.string.testing, score = R.string.Score, number = 1)
            }
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text("Recent", textAlign = TextAlign.Start)
            }
        }
    }


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 390, heightDp = 844)
@Composable
fun HomPre () {

        Home()

}