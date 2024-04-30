package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.SecondaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(text = "Profile", modifier = Modifier.height(56.dp), {})
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(top = 20.dp),
                contentDescription = "Profile Icon")
            Text(text = userName, fontSize = 26.sp)
            Text(text = userEmail, fontSize = 16.sp)
//            UserInput(text = userEmail, inputIcon = Icons.Default.Email,modifier = Modifier.padding(top = 60.dp), isEnabled = false)
            //AltButton(text = "Logout", modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp), {})
            SecondaryButton("View Reports", modifier = Modifier.fillMaxWidth().padding(top = 50.dp), {})
            SecondaryButton("Logout", modifier = Modifier.fillMaxWidth(), {})
        }
    }
}

@Preview(showBackground = true, widthDp = 430, heightDp = 880)
@Composable
fun PreviewProfile(){
    ProfileScreen(userName = "Hamza Waheed", userEmail = "Hamzawaheed057@gmail.com")
}