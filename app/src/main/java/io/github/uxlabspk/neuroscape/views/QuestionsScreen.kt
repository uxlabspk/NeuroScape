package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.uxlabspk.neuroscape.ui.theme.BlueColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.McqsRadioButton
import io.github.uxlabspk.neuroscape.views.components.NavigationButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun QuestionsScreen(
    navController: NavController,
) {
    Column(
        Modifier.background(Color.White)
    ) {
        TopBar(text = "01/10", modifier = Modifier, { navController.navigateUp() })
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            Box (
                Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(OffWhiteColor)
            ) {
                Text(text = "Lorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem ipsimLorem  ipsimLorem ipsimLorem ipsimLorem ipsim", Modifier.padding(10.dp))
            }

            Column (
                Modifier.fillMaxHeight(8/9f).padding(top = 10.dp)
            ) {
                McqsRadioButton(option1 = "Hamza", option2 = "Naveed", option3 = "CEO", option4 = "CTO", option5 = "Killer")
            }

            Row(
                Modifier.fillMaxWidth().padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                NavigationButton(
                    text = "Previous",
                    modifier = Modifier.width(150.dp),
                    isNext = false,
                    {}
                )
                NavigationButton(
                    text = "Next",
                    modifier = Modifier.width(150.dp),
                    isNext = true,
                    {}
                )

            }
        }
    }
}
