package io.github.uxlabspk.neuroscape.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String
){
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxSize()
    ) {
        TopBar(text = "Profile", modifier = Modifier, {})
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile Icon")
            Text(text = userName, fontSize = 26.sp)
            UserInput(text = userEmail, inputIcon = Icons.Default.Email, modifier = Modifier.padding(vertical = 20.dp, horizontal = 12.dp), isEnabled = false)
        }
    }
}

@Preview(showBackground = true, widthDp = 330, heightDp = 880)
@Composable
fun PreviewProfile(){
    ProfileScreen(userName = "Hamza Waheed", userEmail = "Hamzawaheed057@gmail.com")
}