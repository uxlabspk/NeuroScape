package io.github.uxlabspk.neuroscape.views

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.ScanReports
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.GrayColor
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInfo
import java.io.File


@Composable
fun HomeScreen(
    navController: NavController,
) {
    // states
    var user by remember { mutableStateOf<User?>(null) }
    var reports by remember { mutableStateOf<ScanReports?>(null) }
    var bitmapImg by remember { mutableStateOf<ImageBitmap?>(null) }

    // variables
    val context = LocalContext.current
    val time = if (reports != null) reports?.reportTime else user?.lastScan
    val localFile = File(context.filesDir, "image.jpg")

    // Firebase References
    val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
    val uuid = FirebaseAuth.getInstance().currentUser?.uid
    val storageRef = FirebaseStorage.getInstance().getReference("images/")
    val userProfileImg = storageRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString())

    ref.child(uuid.toString()).child("Profile").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                user = snapshot.getValue(User::class.java)
            } else {
                Log.d("TAG", "Invalid Snapshot")
            }
        }

        override fun onCancelled(error: DatabaseError) {}

    })

    ref.child(uuid.toString()).child("Reports").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (dataSnapshot in snapshot.children) {
                reports = dataSnapshot.getValue(ScanReports::class.java)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })

    if (!localFile.exists()) {
        userProfileImg.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            val imageBitmap = bitmap.asImageBitmap()

            bitmapImg = imageBitmap
        }
    } else {
        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
        val imageBitmap = bitmap.asImageBitmap()

        bitmapImg = imageBitmap
    }


    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopBar(text = "Home", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            UserInfo(
                Modifier.background(OffWhiteColor),
                bitmapImg,
                user?.userName.toString(),
                time.toString()
            ) {
                navController.navigate("profile")
            }
            Row(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AltButton("All Scans", Modifier) {
                    navController.navigate("allscans")
                }
                PrimaryButton("New Scans", Modifier, { navController.navigate("newscan") })
            }
            Text(
                "Recents",
                Modifier.padding(vertical = 15.dp),
                fontFamily = SF_Font_Family,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )

            if (reports != null) {
                RecentScans(
                    "${reports?.reportName}",
                    "${reports?.reportResult}",
                    Modifier.background(OffWhiteColor)
                )
            } else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = R.drawable.not_found),
                        contentDescription = "not found",
                        Modifier.height(300.dp)
                    )
                    Text(
                        "No Reports Found",
                        fontFamily = SF_Font_Family,
                        fontWeight = FontWeight.Normal,
                        color = GrayColor
                    )
                }
            }
        }
    }
}


