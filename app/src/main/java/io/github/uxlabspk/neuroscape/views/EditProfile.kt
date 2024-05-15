package io.github.uxlabspk.neuroscape.views

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import java.io.File

@Composable
fun EditProfile(
    navController: NavController
) {
    // states
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var lastScan by remember { mutableStateOf("") }
    var user by remember { mutableStateOf<User?>(null) }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<ImageBitmap?>(null) }

    // variables
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver

    val pickImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                selectedImageUri.value = it
                bitmap.value = uri.asImageBitmap(contentResolver)
            }
        }

    // Firebase References
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val storageRef = FirebaseStorage.getInstance().getReference("images/")
    val userProfileImg = storageRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString())

    databaseRef.child(userId.toString()).child("Profile").addValueEventListener(object :
        ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
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


    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        TopBar(
            text = "Edit Profile",
            modifier = Modifier.height(56.dp)
        ) { navController.navigateUp() }
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

            Button(onClick = { pickImageLauncher.launch("image/*") }) {
                Icon(Icons.Default.Face, contentDescription = null)
            }

            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                label = { Text("Enter your display name...", color = MaterialTheme.colorScheme.onPrimary) },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Email Icon")
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.DarkGray,
                    focusedLabelColor = Color.DarkGray,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(5.dp),
                textStyle = TextStyle(
                    fontFamily = SF_Font_Family,
                    fontWeight = FontWeight.Normal
                )
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text(text = "Enter your email...", color = MaterialTheme.colorScheme.onPrimary) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.DarkGray,
                    focusedLabelColor = Color.DarkGray,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(5.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            PrimaryButton(text = "Update Profile", modifier = Modifier.fillMaxWidth()) {
                selectedImageUri.value?.let {
                    userProfileImg.putFile(it).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
//                            val localFile = File(context.filesDir, "image.jpg")
//                            localFile.delete()
                            Toast.makeText(
                                context,
                                "Profile Successfully Updated. ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
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
