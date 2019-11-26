package com.example.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       buttonSend.setOnClickListener{
        sendMessage()
       }

        buttonCall.setOnClickListener{
            callMe()
        }
    }

    private fun callMe(){
        val phone = "tel:0189570789" //if put http system know u want to go a website/ geo:333.333 to a location
        //An Implicit Intent
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(phone))
        //validate app package manager
        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }


    }

    private fun sendMessage(){
        val message = editTextMessage.text.toString()
        val lucky = editTextLucky.text.toString().toInt()
        //Explicit Intent
        val intent = Intent(this,second::class.java)
        //putExtra to the intent
        intent.putExtra(EXTRA_MESSAGE,message)
        intent.putExtra(EXTRA_LUCKY,lucky)
        ////start the second activity
         //if wan return value have to comment this
        //startActivity(intent)

        //start the second activity with return value
        startActivityForResult(intent,REQUEST_CODE)
    }
    //code >> override method >> onactivityresult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                textViewReply.text = String.format("%s %s", getString(R.string.reply),data?.getStringExtra(EXTRA_REPLY))

            }
        }
    }

    companion object{
        const val EXTRA_MESSAGE = "com.example.intent.MESSAGE"
        const val EXTRA_LUCKY = "com.example.intent.LUCKY"
        const val REQUEST_CODE = 1
        const val EXTRA_REPLY = "com.example.intent.REPLY"
    }
}
