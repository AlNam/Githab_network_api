package com.example.githabrepozitories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githabrepozitories.screens.MainScreen
import com.example.githabrepozitories.ui.theme.GithabRepozitoriesTheme

const val API_KEY = "1f013a3c1f144429b3d152935232502"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithabRepozitoriesTheme {
                MainScreen()
            }
        }
    }
}

fun getData(courseList: MutableList<String>, ctx: Context) {
    val url = "https://api.github.com/users/AlNam/repos"
    val queue = Volley.newRequestQueue(ctx)
    val sRequest = JsonArrayRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                for (i in 0 until response.length()) {
                    val resobj = response.getJSONObject(i)
                    val repo = resobj.getString("name")

                    courseList += repo
                    Log.d("MyLog", "Response: $repo")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
        { error ->
            // in this case we are simply displaying a toast message.
            Toast.makeText(ctx, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        }
    )
    queue.add(sRequest)
}
