package com.example.githabrepozitories

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.githabrepozitories.ui.theme.GithabRepozitoriesTheme

const val API_KEY = "1f013a3c1f144429b3d152935232502"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithabRepozitoriesTheme {
                getData("Moscow", this)
            }
        }
    }
}

private fun getData(city: String, context: Context){
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=$city" +
            "&days=" +
            "3" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
            Log.d("MyLog", "Response: $response")
        },
        {
            Log.d("MyLog", "VolleyError: $it")
        }
    )
    queue.add(sRequest)
}
