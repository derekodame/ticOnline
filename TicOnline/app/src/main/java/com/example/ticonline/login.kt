package com.example.ticonline

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*


class login : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance();

    }

    fun loginE (view: View){

    val email = email.text.toString()
    val password:String = password.text.toString()

        loginset(email,password)

    }

    fun loginset (email:String , password:String){
        var currentuser = mAuth!!.currentUser
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "LogIn was successful", Toast.LENGTH_LONG).show()

                     ifloginAlready()
                } else {
                    Toast.makeText(applicationContext, "Unable to login", Toast.LENGTH_LONG).show()
                }

             }
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
         ifloginAlready()
    }


fun ifloginAlready(){
    var currentUser = mAuth!!.currentUser


    if(currentUser!=null) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email",currentUser!!.email)
        intent.putExtra("uid",currentUser!!.uid)
        startActivity(intent)
    }
}


    fun bRegi (view:View){
        val intent = Intent(this, reg::class.java)

        startActivity(intent)
    }
}
