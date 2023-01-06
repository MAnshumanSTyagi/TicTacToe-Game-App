package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    var Player=true
    var turncount=0
    var boardstatus=Array(3){ IntArray(3)  }
    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board){
            for(button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            Player=true
            turncount=0
            initializeBoardStatus()
            updateDisplay("Player X's Turn")
        }


    }

    private fun initializeBoardStatus() {

        for(i in 0..2){
            for (j in 0..2){
                boardstatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }


    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.button->{
                updatevalue(row=0,col=0,player=Player)

            }
            R.id.button2->{
                updatevalue(row=0,col=1,player=Player)

            }
            R.id.button3->{
                updatevalue(row=0,col=2,player=Player)

            }
            R.id.button4->{
                updatevalue(row=1,col=0,player=Player)

            }
            R.id.button5->{
                updatevalue(row=1,col=1,player=Player)

            }
            R.id.button6->{
                updatevalue(row=1,col=2,player=Player)

            }
            R.id.button7->{
                updatevalue(row=2,col=0,player=Player)

            }
            R.id.button8->{
                updatevalue(row=2,col=1,player=Player)

            }
            R.id.button9->{
                updatevalue(row=2,col=2,player=Player)

            }

        }
        turncount++
        Player=!Player
        if(Player){
            updateDisplay("Player X's Turn")
        }
        else{
            updateDisplay("Player O's Turn")
        }
        if(turncount==9){
            updateDisplay("The Game is Draw")
        }

        checkWinner()


    }

    private fun checkWinner() {
        //Horizontal Rows
        for(i in 0..2){
            if(boardstatus[i][0]==boardstatus[i][1] && boardstatus[i][0]==boardstatus[i][2]){
                if(boardstatus[i][0]==1){
                    updateDisplay("Player X wins!")
                    disableButtons()
                    break
                }
                else if(boardstatus[i][0]==0){
                    updateDisplay("Player O wins!")
                    disableButtons()
                    break
                }
            }
        }

        //Vertical Columns
        for(i in 0..2){
            if(boardstatus[0][i]==boardstatus[1][i] && boardstatus[0][i]==boardstatus[2][i]){
                if(boardstatus[0][i]==1){
                    updateDisplay("Player X wins!")
                    disableButtons()
                    break
                }
                else if(boardstatus[0][i]==0){
                    updateDisplay("Player O wins!")
                    disableButtons()
                    break
                }
            }
        }

        //First Diagonal
        if(boardstatus[0][0]==boardstatus[1][1] && boardstatus[0][0]==boardstatus[2][2]){
            if(boardstatus[1][1]==1){
                updateDisplay("Player X wins!")
                disableButtons()
            }
            else if(boardstatus[1][1]==0){
                updateDisplay("Player O wins!")
                disableButtons()

            }
        }
        //Second Diagonal
        if(boardstatus[0][2]==boardstatus[1][1] && boardstatus[0][2]==boardstatus[2][0]){
            if(boardstatus[1][1]==1){
                updateDisplay("Player X wins!")
                disableButtons()

            }
            else if(boardstatus[1][1]==0){
                updateDisplay("Player O wins!")
                disableButtons()

            }
        }

    }

    private fun disableButtons() {
        for(i in board){
            for(button in i){
                button.isEnabled=false
            }
        }
    }

    private fun updateDisplay(s: String) {
        displayTv.text=s

    }

    private fun updatevalue(row: Int, col: Int, player: Boolean) {
        val text=if(player) "X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        boardstatus[row][col]=value

    }
}