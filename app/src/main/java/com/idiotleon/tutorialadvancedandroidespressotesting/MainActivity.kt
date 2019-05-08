package com.idiotleon.tutorialadvancedandroidespressotesting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_IEADS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToIdeaActivity(v: View) {
        val btn = v as Button

        val intent = Intent(this, IdeasActivity::class.java)
        intent.putExtra(IdeasActivity.KEY_THEME, btn.text)

        startActivityForResult(intent, REQUEST_CODE_IEADS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_IEADS -> {
                (et_name as TextView).text = data?.getStringExtra(IdeasActivity.KEY_NAME)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
