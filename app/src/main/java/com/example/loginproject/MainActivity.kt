package com.example.loginproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginproject.Network.Network
import com.example.loginproject.Network.response.Items
import com.example.loginproject.Network.response.LoginApiResponse
import com.example.loginproject.Network.response.SearchResultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editTextUserName = findViewById<EditText>(R.id.mailEditText)
        val editTextPassword = findViewById<EditText>(R.id.passwordEditText)
        val buttonLogin = findViewById<Button>(R.id.signInButton)

        buttonLogin.setOnClickListener {
            val userName = editTextUserName.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (userName.isNotEmpty() && password.isNotEmpty()) {
                login(userName, password)
            } else {

            }
        }
    }

    private fun login(userName: String, password: String) {
        val credentials = LoginApiResponse(userName, password)

        Network.service.loginUser(credentials).enqueue(object : Callback<SearchResultResponse> {
            override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    result?.let {
                        if (it.success) {

                            Log.d("MainActivity", "Login successful")
                        } else {
                            Log.e("MainActivity", "Login failed: ${it.items.message}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ${t.message}")
            }
        })
    }
}