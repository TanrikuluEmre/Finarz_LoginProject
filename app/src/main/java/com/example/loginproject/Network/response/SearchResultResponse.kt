package com.example.loginproject.Network.response

import android.content.ClipData.Item

data class SearchResultResponse(
     val items:Items,
     val success:Boolean
)

data class Items(val redirectToTwoFactor:Boolean?,
                val token:String,
                val isSuccess:Boolean?,
                val message:String)

data class LoginApiResponse(val userName: String, val password: String)

