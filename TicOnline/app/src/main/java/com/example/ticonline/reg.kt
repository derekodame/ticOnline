package com.example.ticonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_reg.*

class reg : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var database=FirebaseDatabase.getInstance()
    private var refddatabase = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        mAuth = FirebaseAuth.getInstance()
    }
    fun RegB (view: View){

        val personn = rPerson.text.toString()
        val email = rEmail.text.toString()
        val password:String = rPassword.text.toString()

        if(personn!=""){
            if((email!="" )){
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (password != "") {
                        if (password.length>6) {
                            registerr(email, password)
                        } else {
                            Toast.makeText(applicationContext, "password length too small", Toast.LENGTH_LONG)
                                .show()
                        }
                    }else {
                        Toast.makeText(applicationContext, "Input password", Toast.LENGTH_LONG)
                        .show()
                    }
                }else {
                    Toast.makeText(applicationContext, "Input correct eamil", Toast.LENGTH_LONG)
                        .show() }
            }else{Toast.makeText(applicationContext, "Input email", Toast.LENGTH_LONG).show() }
        }else {Toast.makeText(applicationContext, "Input Name", Toast.LENGTH_LONG).show() }



    }

    fun registerr (email:String , password:String){
        var currentuser = mAuth!!.currentUser
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "LogIn was successful", Toast.LENGTH_LONG).show()

                    ifloginAlready()
                } else {
                    Toast.makeText(applicationContext, "Unable to login", Toast.LENGTH_LONG).show()
                }

            }
    }



    fun ifloginAlready(){
        var currentUser = mAuth!!.currentUser


        if(currentUser!=null) {

            refddatabase.child("Users").child(currentUser!!.uid).setValue(currentUser!!.email)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email",currentUser!!.email)
            intent.putExtra("uid",currentUser!!.uid)
            startActivity(intent)
        }
    }
}
