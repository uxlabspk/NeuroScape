package io.github.uxlabspk.neuroscape.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.uxlabspk.neuroscape.R
import io.github.uxlabspk.neuroscape.data.ScanReports
import io.github.uxlabspk.neuroscape.ui.theme.SF_Font_Family
import io.github.uxlabspk.neuroscape.views.components.AltButton
import io.github.uxlabspk.neuroscape.views.components.CustomDialog
import io.github.uxlabspk.neuroscape.views.components.RecentScans
import io.github.uxlabspk.neuroscape.views.components.TopBar

@Composable
fun AllScans(
    navController: NavController
) {
    // states
    var reports by remember { mutableStateOf<List<ScanReports?>>(emptyList()) }
    var isConfirmed by remember { mutableStateOf(false) }

    // variables
    val context = LocalContext.current

    // Firebase References
    val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users")
    val uuid = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(key1 = uuid) {
        val reportsList = mutableListOf<ScanReports>()
        ref.child(uuid.toString()).child("Reports")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnapshot in snapshot.children) {
                        val report = dataSnapshot.getValue(ScanReports::class.java)
                        if (report != null) {
                            reportsList.add(report)
                        }
                    }
                    reports = reportsList
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    if (isConfirmed) {
        if (reports.isNotEmpty()) {
            CustomDialog(
                onDismissRequest = {
                    isConfirmed = false
                },
                onConfirmation = {
                    isConfirmed = false
                    FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(FirebaseAuth.getInstance().currentUser?.uid.toString()).child("Reports")
                        .removeValue().addOnSuccessListener {
                            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show()
                            reports = emptyList()
                        }.addOnFailureListener {
                            Toast.makeText(
                                context,
                                "Unknown Error Occur!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                },
                dialogTitle = "Confirm",
                dialogText = "Are you sure to delete?"
            )
        }

    }

    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        TopBar(text = "All Scans", modifier = Modifier.height(54.dp)) {
            navController.navigateUp()
        }
        Column(
            Modifier
                .padding(20.dp, 10.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total Reports : ${reports.size}",
                    fontFamily = SF_Font_Family,
                    fontWeight = FontWeight.Normal
                )
                AltButton(text = "Delete All", modifier = Modifier) { isConfirmed = true }
            }
            if (reports.isNotEmpty()) {
                LazyColumn {
                    items(reports) { report ->
                        RecentScans(
                            "${report?.reportName}",
                            "${report?.reportResult}",
                            Modifier.background(MaterialTheme.colorScheme.surface)
                        )
                    }
                }
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
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = SF_Font_Family,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

        }
    }
}


