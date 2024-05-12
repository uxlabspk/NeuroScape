package io.github.uxlabspk.neuroscape.views

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
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
import io.github.uxlabspk.neuroscape.ui.theme.GrayColor
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import java.util.jar.Manifest

@Composable
fun EditProfile(

) {
    var username by remember { mutableStateOf("") }

    var email by rememberSaveable { mutableStateOf("") }

    var lastScan by remember { mutableStateOf("") }

    val context = LocalContext.current
    var user by remember { mutableStateOf <User?> (null) }
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    val user_ID = FirebaseAuth.getInstance().currentUser?.uid

    databaseRef.child(user_ID.toString()).child("Profile").addValueEventListener( object :
        ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if(snapshot.exists()){
                user = snapshot.getValue(User::class.java)
                username = user?.userName.toString()
                email = user?.userEmail.toString()
                lastScan = user?.lastScan.toString()
            } else {
                Log.d("Error", "Invalid snapshot")
            }
        }
        override fun onCancelled(error: DatabaseError) {}
    })

    val profileUpdates = userProfileChangeRequest {
        displayName = username
    }


    // Image Picker for profile/
    val lifecycleOwner = LocalLifecycleOwner.current
    val contentResolver: ContentResolver = context.contentResolver

    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<ImageBitmap?>(null) }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri.value = it
            bitmap.value = uri.asImageBitmap(contentResolver)
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopBar(text = "Edit Profile", modifier = Modifier.height(56.dp)) {}
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            bitmap.value?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
            }

//            PrimaryButton(text = "", modifier = Modifier.clip(RoundedCornerShape(100))) {
//                pickImageLauncher.launch("image/*")
//            }
            Button(onClick = {pickImageLauncher.launch("image/*")}) {
                Icon(Icons.Default.Face, contentDescription = null)
            }

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
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Enter your email...") },
                leadingIcon = {
                    Icon(
//                        painter = painterResource(id = R.drawable.ic_password),
                        Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
//                trailingIcon = {
//                    IconButton(onClick = {
//                        passwordVisibility = !passwordVisibility
//                    }) {
//                        Icon(
//                            painter = icon,
//                            contentDescription = "visibility icon"
//                        )
//                    }
//                },
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
//                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
//                visualTransformation = if (passwordVisibility) VisualTransformation.None
//                else PasswordVisualTransformation()
            )

            PrimaryButton(text = "Update Profile", modifier = Modifier.fillMaxWidth()) {

            }
        }

    }

}

fun Uri.asImageBitmap(contentResolver: ContentResolver): ImageBitmap? {
    return try {
        val inputStream = contentResolver.openInputStream(this)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()
        bitmap.asImageBitmap()
    } catch (e: Exception) {
        null
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EditProfilePreview() {
    EditProfile()
}