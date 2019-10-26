package com.example.camila_hw1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        private const val EDIT_ACTIVITY_KEY = "textFromEditActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        text_edit.setText(intent.extras?.getString("textFromMainActivity"))
        save_btn.setOnClickListener(this)
        save_btn.isEnabled = text_edit.text.isNotEmpty()

        text_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                save_btn.isEnabled = s?.isNotEmpty() ?: false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onClick(view: View?) {
        when(view) {
            save_btn -> save()
        }
    }


    private fun save() {
        val intent = Intent()
        intent.putExtra(EDIT_ACTIVITY_KEY, text_edit.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
