package jam.rs.wllmhllbngpp.database

import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.gson.Gson
import java.text.FieldPosition

class FirebaseDB {
    lateinit var databaseReference: DatabaseReference

    fun getAbout(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("App").child("About")
        return databaseReference
    }

    fun getHow(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("App").child("Howto")
        return databaseReference
    }

    fun getQuestions(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("App").child("Questions")
        return databaseReference
    }

    fun getTypes(): DatabaseReference {
        val database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("App").child("Types")
        return databaseReference
    }
}