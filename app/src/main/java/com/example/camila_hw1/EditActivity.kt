package com.example.camila_hw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import com.example.camila_hw1.R.id.text_edit

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val editText: EditText = findViewById(text_edit)
        editText.setText(intent.extras?.getString("textFromMainActivity"))
        val saveButton: Button = findViewById(R.id.save_btn)
        saveButton.isEnabled = editText.text.isNotEmpty()

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                saveButton.isEnabled = s?.isNotEmpty() ?: false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    fun save(@Suppress("UNUSED_PARAMETER") view: View) {
        val editText: EditText = findViewById(text_edit)
        val intent = Intent()
        intent.putExtra("textFromEditActivity", editText.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
