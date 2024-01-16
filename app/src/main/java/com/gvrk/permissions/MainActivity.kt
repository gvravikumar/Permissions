package com.gvrk.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.gvrk.permissions.ui.theme.PermissionsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val contactsPermissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
    val contactsPermissionLauncher = rememberLauncher()

    PermissionRequired(
        permissionState = contactsPermissionState,
        permissionNotGrantedContent = {
            // UI to show when permission is not granted
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Contact permission is required to proceed.")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val hasContactsPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_GRANTED
                    if (!hasContactsPermission)
                        contactsPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                }) {
                    Text("Request Permission")
                }
            }
        },
        content = {
            // UI to show when permission is granted
            // Your main content goes here
            Text("Contacts Permission Granted! Proceed with your UI.")
        },
        permissionNotAvailableContent = {
            Text("Contacts Permission not available! Goto app settings.")
        }
    )
}

@Composable
fun rememberLauncher(): ActivityResultLauncher<String> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, proceed with accessing contacts
            // Call your method to access contacts here
        } else {
            // Permission denied, handle accordingly (e.g., show a message or disable features)
        }
    }
}

