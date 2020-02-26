package com.example.ticonline

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //the button clicks events
    fun  bclick (view: View){
        val bselect= view as Button
        var cellid:Int= 0
        when(bselect.id){
            R.id.b1->cellid=1
            R.id.b2->cellid=2
            R.id.b3->cellid=3
            R.id.b4->cellid=4
            R.id.b5->cellid=5
            R.id.b6->cellid=6
            R.id.b7->cellid=7
            R.id.b8->cellid=8
            R.id.b9->cellid=9

        }

        //Toast.makeText(this, "ID: " + cellid, Toast.LENGTH_LONG).show()
        PlayGame(cellid,bselect)
    }


    //Function for the games.....
    var player1= ArrayList<Int>()
    var player2= ArrayList<Int>()
    var ActivePlayer = 1

    fun PlayGame(cellid:Int, bselect: Button){

        if(ActivePlayer==1){
            bselect.text="X"
            bselect.setBackgroundColor(Color.GREEN)
            player1.add(cellid)
            ActivePlayer=2
            autoplay()
        }else {
            bselect.text="O"
            bselect.setBackgroundColor(Color.BLUE)
            player2.add(cellid)
            ActivePlayer=1
        }

        bselect.isEnabled=false
        checkWinner()
    }


    fun resett (winner:Int){
        if(winner==1){
            val intent = Intent(this,this::class.java)



            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Player 1 wins. Click to Reset")
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    0 -> { startActivity(intent)}

                }
            }
            pictureDialog.show()
        } else {
            val intent = Intent(this,this::class.java)



            val pictureDialog = AlertDialog.Builder(this)
            pictureDialog.setTitle("Select Action")
            val pictureDialogItems = arrayOf("Player 2 wins. Click to Reset")
            pictureDialog.setItems(pictureDialogItems
            ) { dialog, which ->
                when (which) {
                    0 -> { startActivity(intent)}

                }
            }
            pictureDialog.show()
        }
    }

    //function for winner
    fun checkWinner(){
        var winner=-1

        //row 1
        if(player1.contains(1)&& player1.contains(2)&&player1.contains(3)){
            winner=1



        }

        if(player2.contains(1)&& player2.contains(2)&&player2.contains(3)){
            winner=2
        }

        //row 2
        if(player1.contains(4)&& player1.contains(5)&&player1.contains(6)){
            winner=1
        }

        if(player2.contains(4)&& player2.contains(5)&&player2.contains(6)){
            winner=2
        }

        //row 3

        if(player1.contains(7)&& player1.contains(8)&&player1.contains(9)){
            winner=1
        }

        if(player2.contains(7)&& player2.contains(8)&&player2.contains(9)){
            winner=2
        }

        //column 1

        if(player1.contains(1)&& player1.contains(4)&&player1.contains(7)){
            winner=1
        }

        if(player2.contains(1)&& player2.contains(4)&&player2.contains(7)){
            winner=2
        }

        //column 2

        if(player1.contains(2)&& player1.contains(5)&&player1.contains(8)){
            winner=1
        }

        if(player2.contains(2)&& player2.contains(5)&&player2.contains(8)){
            winner=2
        }

        //column 1

        if(player1.contains(3)&& player1.contains(6)&&player1.contains(9)){
            winner=1
        }

        if(player2.contains(3)&& player2.contains(6)&&player2.contains(9)){
            winner=2
        }

        if(winner !=-1){

            if(winner==1){
                Toast.makeText(this, "player 1 wins" , Toast.LENGTH_LONG).show()
                resett(winner)

            }else {
                Toast.makeText(this, "player 2 wins", Toast.LENGTH_LONG).show()
                resett(winner)
            }

        } else {
            //Toast.makeText(applicationContext, "done", Toast.LENGTH_LONG).show()

        }



    }

    fun autoplay(){
        var emptyCell =ArrayList<Int>()
        for (cellid in 1..9){
            if(!(player1.contains(cellid)|| player2.contains(cellid))){
                emptyCell.add(cellid)

            }
        }


        val randIndex= Random.nextInt(until = emptyCell.size-0)+0
        val cellid= emptyCell[randIndex]

        var bselect: Button?

        when (cellid){
            1->bselect=b1
            2->bselect=b2
            3->bselect=b3
            4->bselect=b4
            5->bselect=b5
            6->bselect=b6
            7->bselect=b7
            8->bselect=b8
            9->bselect=b9
            else->{
                bselect=b1
            }

        }

        PlayGame(cellid,bselect)
    }
}
