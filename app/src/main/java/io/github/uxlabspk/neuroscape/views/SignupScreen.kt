package io.github.uxlabspk.neuroscape.views

import android.os.Build
import android.os.Bundle
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignupScreen(
    navController: NavController,
) {
    // states
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    // variables
    val icon =
        if (passwordVisibility) painterResource(id = R.drawable.ic_visible)
        else painterResource(id = R.drawable.ic_invisible)

    val isUserError = false
    val isEmailError = false
    val isPasswordError = false

    val context = LocalContext.current


    Column(Modifier.background(MaterialTheme.colorScheme.background)) {
        TopBar(text = "Sign up", modifier = Modifier.height(54.dp), { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.width(180.dp),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                label = { Text("Enter your name...") },
                isError = isUserError,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Email Icon")
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

            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                label = { Text("Enter your name...") },
                isError = isEmailError,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
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

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = password,
                onValueChange = {
                    password = it
                },
                isError = isPasswordError,
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = "Password Icon"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "visibility icon"
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.DarkGray,
                    focusedLabelColor = Color.DarkGray,
                    focusedLeadingIconColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.LightGray,
                    cursorColor = Color.Black
                ),
                shape = RoundedCornerShape(5.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )

            PrimaryButton(text = "Sign up", modifier = Modifier.fillMaxWidth()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            val date = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            val user = User(username, email, date)
                            FirebaseDatabase.getInstance().getReference()
                                .child("Users")
                                .child(
                                    FirebaseAuth
                                        .getInstance()
                                        .uid.toString()
                                )
                                .child("Profile")
                                .setValue(user)
                                .addOnSuccessListener {
                                    navController.addOnDestinationChangedListener { navController: NavController, navDestination: NavDestination, bundle: Bundle? ->
                                        if (navDestination.route == "signup") {
                                            Toast.makeText(
                                                context,
                                                "Authentication successful",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.navigate("home")
                                        }
                                    }
                                }.addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Something abc went wrong : ${task.exception?.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            Toast.makeText(
                                context,
                                "Something ghv went wrong : ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

            Row(
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Already have an account? ",
                    fontFamily = SF_Font_Family,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                TextButton(
                    onClick = { navController.navigate("signin") }
                ) {
                    Text(
                        "Sign In",
                        fontFamily = SF_Font_Family,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

        }
    }
}

