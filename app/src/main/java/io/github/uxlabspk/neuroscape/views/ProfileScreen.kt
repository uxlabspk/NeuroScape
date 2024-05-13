package io.github.uxlabspk.neuroscape.views

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.storage.FirebaseStorage
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.SecondaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar
import java.io.File


@Composable
fun ProfileScreen(
    navController: NavController
){
    var user by remember { mutableStateOf <User?> (null) }
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    val user_ID = FirebaseAuth.getInstance().currentUser?.uid

    databaseRef.child(user_ID.toString()).child("Profile").addValueEventListener( object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            if(snapshot.exists()){
                user = snapshot.getValue(User::class.java)
            } else {
                Log.d("Error", "Invalid snapshot")
            }
        }
        override fun onCancelled(error: DatabaseError) {}
    })

    var storageRef = FirebaseStorage.getInstance().getReference("images/")
    val userProfileImg = storageRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString())

    var bitmapImg by remember { mutableStateOf<ImageBitmap?>(null) }

    val context = LocalContext.current

    val localFile = File(context.filesDir, "image.jpg") // File.createTempFile("images", "jpg")

    if (!localFile.exists()) {
        userProfileImg.getFile(localFile).addOnSuccessListener {
            var bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            val imageBitmap = bitmap.asImageBitmap()

            bitmapImg = imageBitmap
        }
    } else {
        var bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
        val imageBitmap = bitmap.asImageBitmap()

        bitmapImg = imageBitmap
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopBar(text = "Profile", modifier = Modifier.height(56.dp), { navController.navigateUp() })
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter =  bitmapImg?.let { BitmapPainter(it) } ?: painterResource(id = R.drawable.ic_account),
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .size(120.dp),
                contentDescription = null
            )
            Text(text = user?.userName.toString(), fontSize = 26.sp, fontFamily = SF_Font_Family, fontWeight = FontWeight.Medium)
            Text(text = user?.userEmail.toString(), fontSize = 16.sp, fontFamily = SF_Font_Family, fontWeight = FontWeight.Normal)

            AltButton("Edit Profile", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp), ) { navController.navigate("editprofile")}
            AltButton("View Reports", modifier = Modifier
                .fillMaxWidth()) { navController.navigate("allscans")}
            AltButton("Logout", modifier = Modifier.fillMaxWidth(), ){
                FirebaseAuth.getInstance().signOut()
                navController.addOnDestinationChangedListener { controller, destination, _ ->
                    if(destination.route == "profile") {
                        if (FirebaseAuth.getInstance().currentUser == null) {
                            controller.navigate("Welcome")
                        }
                    }
                }
            }
        }
    }
}