package org.techm.optus.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techm.optus.R

/**
 * @class{MainActivity} -> starts the main application
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
