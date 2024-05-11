package io.github.uxlabspk.neuroscape.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun EditProfile(

) {
    var username by remember {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val icon =
        if (passwordVisibility) painterResource(id = R.drawable.ic_visible)
        else painterResource(id = R.drawable.ic_invisible)


    val context = LocalContext.current

    var user by remember { mutableStateOf <User?> (null) }
//    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
//    val user_ID = FirebaseAuth.getInstance().currentUser?.uid

//    databaseRef.child(user_ID.toString()).child("Profile").addValueEventListener( object :
//        ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            if(snapshot.exists()){
//                user = snapshot.getValue(User::class.java)
//            } else {
//                Log.d("Error", "Invalid snapshot")
//            }
//        }
//        override fun onCancelled(error: DatabaseError) {}
//    })

    val profileUpdates = userProfileChangeRequest {
        displayName = username
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopBar(text = "Edit Profile", modifier = Modifier.height(56.dp)) {}
        Column(
            Modifier.fillMaxSize().padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .padding(bottom = 20.dp),
                contentDescription = "Profile Icon")

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                label = { Text("Enter your display name...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Email Icon")
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
                )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = password,
                onValueChange = {
                    password = it
                },
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

            PrimaryButton(text = "Update Profile", modifier = Modifier.fillMaxWidth()) {

            }
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfilePreview() {
    EditProfile()
}