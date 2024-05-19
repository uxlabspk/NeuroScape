package io.github.uxlabspk.neuroscape.views

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
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
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.ProgressDialog
import io.github.uxlabspk.neuroscape.views.components.TopBar


@Composable
fun EditProfile(
    navController: NavController
) {
    // states
    var username by remember { mutableStateOf("") }
    val selectedImageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember { mutableStateOf(false) }

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

    ProgressDialog(isLoading, "Updating")

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
                    contentScale = ContentScale.Crop,
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

            PrimaryButton(text = "Update Profile", modifier = Modifier.fillMaxWidth()) {
                if(username.isNotEmpty()) {
                    databaseRef.child(userId.toString()).child("Profile").child("userName").setValue(username)
                        .addOnCompleteListener {task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Username Successfully Updated.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                username = ""
                            } else {
                                Toast.makeText(
                                    context,
                                    "Something went wrong",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }

                isLoading = true
                selectedImageUri.value?.let {
                    userProfileImg.putFile(it).addOnSuccessListener {
                        userProfileImg.downloadUrl.addOnSuccessListener {uri ->
                            databaseRef.child(userId.toString()).child("Profile").child("userPhotoUrl").setValue(uri.toString())
                            isLoading = false
                            Toast.makeText(
                                context,
                                "Profile Successfully Updated.",
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
