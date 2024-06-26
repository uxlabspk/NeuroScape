package io.github.uxlabspk.neuroscape.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.TopBar


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {
    // states
    var user by remember { mutableStateOf<User?>(null) }

    // Firebase Reference
    val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    databaseRef.child(userId.toString()).child("Profile")
        .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(User::class.java)
                } else {
                    Log.d("Error", "Invalid snapshot")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

    val painter: Painter = if (user?.userPhotoUrl.toString().isNotEmpty())
        rememberImagePainter(data = user?.userPhotoUrl.toString())
    else
        painterResource(id = R.drawable.ic_account)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(text = "Profile", modifier = Modifier.height(56.dp)) { navController.navigateUp() }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .size(120.dp),
                contentDescription = "Profile photo"
            )
            Text(
                text = user?.userName.toString(),
                fontSize = 26.sp,
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = user?.userEmail.toString(),
                fontSize = 16.sp,
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimary
            )

            AltButton(
                "Edit Profile",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
            ) { navController.navigate("editprofile") }
            AltButton(
                "View Reports", modifier = Modifier
                    .fillMaxWidth()
            ) { navController.navigate("allscans") }
            AltButton("Logout", modifier = Modifier.fillMaxWidth()) {
                FirebaseAuth.getInstance().signOut()
                navController.addOnDestinationChangedListener { controller, destination, _ ->
                    if (destination.route == "profile") {
                        if (FirebaseAuth.getInstance().currentUser == null) {
                            controller.navigate("Welcome")
                        }
                    }
                }
            }
        }
    }
}