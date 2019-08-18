package co.nyenjes.safari.managers

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream

class FirebaseManager {
    init{
        val serviceAccount = FileInputStream("resources/keys/safari-service-key.json")

        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://safari-f1c17.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)
    }
}