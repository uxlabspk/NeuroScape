package io.github.uxlabspk.neuroscape.views

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.ScanReports

import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInput
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewScanScreen(
    navController: NavController,
) {
    var reportName by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val uid = FirebaseAuth.getInstance().currentUser?.uid

    Column(Modifier.background(Color.White)) {
        TopBar(text = "New Scan", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            TextField(
                value = reportName,
                onValueChange = { reportName = it},
                Modifier.fillMaxWidth().padding(vertical = 4.dp),
                label = { Text("Enter report name...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Email Icon")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.DarkGray,
                    focusedLabelColor = Color.DarkGray,
                    focusedLeadingIconColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,

                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(5.dp),
                singleLine = true,

                )
            PrimaryButton(text = "Start", modifier = Modifier.fillMaxWidth()) {
                val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                val scanReport = ScanReports(reportName, date, "")
                FirebaseDatabase.getInstance().getReference().child("Users").child(uid.toString()).child("Reports").child(System.currentTimeMillis().toString()).setValue(scanReport).addOnSuccessListener {
                    navController.navigate("questionsScreen")
                }.addOnFailureListener {
                    Toast.makeText(context, "Something wents wrong.", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}

