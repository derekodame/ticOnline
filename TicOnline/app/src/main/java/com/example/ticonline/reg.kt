package com.example.ticonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reg.*

class reg : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        mAuth = FirebaseAuth.getInstance()
    }
    fun RegB (view: View){

        val personn = rPerson.text.toString()
        val email = rEmail.text.toString()
        val password:String = rPassword.text.toString()

        registerr(email,password)

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
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email",currentUser!!.email)
            intent.putExtra("uid",currentUser!!.uid)
            startActivity(intent)
        }
    }
}
