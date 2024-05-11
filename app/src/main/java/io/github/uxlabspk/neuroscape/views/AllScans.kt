package io.github.uxlabspk.neuroscape.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.uxlabspk.neuroscape.data.ScanReports
import io.github.uxlabspk.neuroscape.ui.theme.OffWhiteColor
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun AllScans(
    navController: NavController
) {
    var reports by remember { mutableStateOf<List<ScanReports?>>(emptyList()) }

    val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
    val uuid = FirebaseAuth.getInstance().currentUser?.uid
    
    LaunchedEffect(key1 = uuid) {
        val reportsList = mutableListOf<ScanReports>()

        ref.child(uuid.toString()).child("Reports").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshot in snapshot.children) {
                    val report = dataSnapshot.getValue(ScanReports::class.java)
                    if (report != null) {
                        reportsList.add(report)
                    }
                }
                reports = reportsList
            }

            override fun onCancelled(error: DatabaseError) { TODO("Not yet implemented") }
        })
        
    }

    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()) {
        TopBar(text = "All Scans", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(20.dp, 10.dp)
        ) {
            LazyColumn {
                items(reports) {report ->
                    RecentScans("${report?.reportName}", "${report?.reportResult}", Modifier.background(OffWhiteColor))
                }
            }
        }
    }
}