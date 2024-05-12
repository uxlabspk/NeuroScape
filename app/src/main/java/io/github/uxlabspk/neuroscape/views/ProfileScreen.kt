package io.github.uxlabspk.neuroscape.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
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
import io.github.uxlabspk.neuroscape.data.User
import io.github.uxlabspk.neuroscape.views.components.SecondaryButton
import io.github.uxlabspk.neuroscape.views.components.TopBar


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
            Icon(
                imageVector = Icons.Default.AccountCircle,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(top = 20.dp),
                contentDescription = "Profile Icon")
            Text(text = user?.userName.toString(), fontSize = 26.sp)
            Text(text = user?.userEmail.toString(), fontSize = 16.sp)
            SecondaryButton("Edit Profile", modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp), ) { navController.navigate("editprofile")}
            SecondaryButton("View Reports", modifier = Modifier
                .fillMaxWidth()) { navController.navigate("allscans")}
            SecondaryButton("Logout", modifier = Modifier.fillMaxWidth(), ){
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