package io.github.uxlabspk.neuroscape.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import io.github.uxlabspk.neuroscape.data.ScanReports
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.PrimaryButton
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar
import io.github.uxlabspk.neuroscape.views.components.UserInfo


@Composable
fun HomeScreen(
    navController: NavController,
) {
    var user by remember { mutableStateOf<User?>(null) }
    var reports by remember {
        mutableStateOf<ScanReports?>(null)
    }
    val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
    val uuid = FirebaseAuth.getInstance().currentUser?.uid

    ref.child(uuid.toString()).child("Profile").addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                user = snapshot.getValue(User::class.java)
            } else {
                Log.d("TAG", "Invalid Snapshot")
            }
        }

        override fun onCancelled(error: DatabaseError) {}

    })
//    ref.child(uuid.toString()).child("Reports").child("1715086729691").addValueEventListener(object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            if (snapshot.exists()){
//                reports = snapshot.getValue(ScanReports::class.java)
//            } else {
//                Log.d("Tag", "Invalid Snapshot")
//            }
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//            TODO("Not yet implemented")
//        }
//    })


    ref.child(uuid.toString()).child("Reports").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()){
                val reportID = snapshot.key
                Log.d("Tag", "${reportID}")
                reports = snapshot.getValue(ScanReports::class.java)
            } else {
                Log.d("Tag", "Invalid Snapshot")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    })

    Column(
        Modifier.background(Color.White)
    ) {
        TopBar(text = "Home", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        ) {
            UserInfo(Modifier.background(OffWhiteColor), user?.userName.toString(), user?.lastScan.toString()) {
                navController.navigate("profile")
            }
            Row(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AltButton("All Scans", Modifier) {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("Welcome")
                }
                PrimaryButton("New Scans", Modifier, { navController.navigate("newscan")})
            }
            Text("Resents", Modifier.padding(vertical = 15.dp), fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            LazyColumn {
                items(4) { item ->
                    RecentScans("${reports?.reportName}", 3, true, Modifier.background(OffWhiteColor))
                }
            }
        }
    }
}

