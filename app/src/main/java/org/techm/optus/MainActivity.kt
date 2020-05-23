package org.techm.optus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import org.techm.optus.data.model.user.UserModel
import org.techm.optus.data.network.APIService
import org.techm.optus.data.network.APIServiceImpl
import org.techm.optus.data.repository.UserRepository
import org.techm.optus.ui.factory.user.UserViewModelFactory
import org.techm.optus.ui.viewmodel.user.UserViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
