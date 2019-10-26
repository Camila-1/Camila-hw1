package com.example.camila_hw1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        private const val REQUEST_CODE = 1
        private const val MAIN_ACTIVITY_KEY = "textFromMainActivity"
        private const val EDIT_ACTIVITY_KEY = "textFromEditActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt.movementMethod = ScrollingMovementMethod()
        send_btn.setOnClickListener(this)
        enter_btn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view) {
            send_btn -> newEmail()
            enter_btn -> enterText()
        }
    }

    private fun enterText() {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra(MAIN_ACTIVITY_KEY, txt.text.toString())
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode != REQUEST_CODE && resultCode != Activity.RESULT_OK) return

        txt.text = data?.extras?.getString(EDIT_ACTIVITY_KEY)
        send_btn.isEnabled = txt.text.isNotEmpty()
    }

    private fun newEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject))
            putExtra(Intent.EXTRA_TEXT, txt.text.toString())
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            val toast: Toast = Toast.makeText(applicationContext, getString(R.string.toast), Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
