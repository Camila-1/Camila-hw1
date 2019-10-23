package com.example.camila_hw1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.crashlytics.android.Crashlytics

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.txt)
        textView.movementMethod = ScrollingMovementMethod()
    }

    fun enterText(@Suppress("UNUSED_PARAMETER") view: View) {
        val textView: TextView = findViewById(R.id.txt)
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("textFromMainActivity", textView.text.toString())
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != Activity.RESULT_OK) return
        val textView: TextView = findViewById(R.id.txt)
        textView.text = data?.extras?.getString("textFromEditActivity")
        val sendButton: Button = findViewById(R.id.send_btn)
        sendButton.isEnabled = textView.text.isNotEmpty()

    }

    fun newEmail(@Suppress("UNUSED_PARAMETER") view: View) {
        val textView: TextView = findViewById(R.id.txt)

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, "Subject")
            putExtra(Intent.EXTRA_TEXT, textView.text.toString())
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val toast: Toast = Toast.makeText(applicationContext, "email app not found", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
