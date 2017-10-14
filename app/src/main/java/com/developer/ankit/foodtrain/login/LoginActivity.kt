package com.developer.ankit.foodtrain.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.developer.ankit.foodtrain.R
import com.developer.ankit.foodtrain.foodtrains.FoodTrainsActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : AppCompatActivity() {

    private val callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_button.setReadPermissions("email")

        login_button.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onCancel() {}

            override fun onError(error: FacebookException?) {
                Timber.e(error.toString())
                Toast.makeText(this@LoginActivity, getText(R.string.login_error), Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onSuccess(result: LoginResult?) {
                startFoodTrainsActivity()
            }
        })
    }

    private fun startFoodTrainsActivity() {
        val intent = Intent(this, FoodTrainsActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
