package com.example.ticonline

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*


class login : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var database= FirebaseDatabase.getInstance()
    private var refddatabase = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance();

    }

    fun loginE (view: View){

    val email = email.text.toString()
    val password:String = password.text.toString()

        if(email!=""){
            if(password!=""){
                loginset(email,password)
            }else {Toast.makeText(applicationContext, "Input password", Toast.LENGTH_LONG).show()
            }
        }else{Toast.makeText(applicationContext, "Input email", Toast.LENGTH_LONG).show() }



    }

    fun loginset (eemail:String , ppassword:String){

     email.isEnabled = false
     password.isEnabled = false

        var currentuser = mAuth!!.currentUser
        mAuth!!.signInWithEmailAndPassword(eemail, ppassword)
            .addOnCompleteListener(this){task ->
                Toast.makeText(applicationContext, "Processing Data", Toast.LENGTH_LONG).show()

                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "LogIn was successful", Toast.LENGTH_LONG).show()

                     ifloginAlready()
                } else {
                    Toast.makeText(applicationContext, "Unable to login", Toast.LENGTH_LONG).show()
                    email.isEnabled = true
                    password.isEnabled = true
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
        refddatabase.child("Users").child(currentUser.uid).setValue(currentUser.email)


        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email",currentUser.email)
        intent.putExtra("uid",currentUser.uid)
        startActivity(intent)
    }
}


    fun bRegi (view:View){
        val intent = Intent(this, reg::class.java)

        startActivity(intent)
    }

    override fun onBackPressed() {

       // Toast.makeText(applicationContext, "Disabled!!", Toast.LENGTH_LONG).show()
       // moveTaskToBack(true)

       // System.exit(0)

        finishAffinity()
    }
}
