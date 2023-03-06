package com.example.githabrepozitories

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.githabrepozitories.data.RepoModel
import com.example.githabrepozitories.screens.MainScreen
import com.example.githabrepozitories.ui.theme.GithabRepozitoriesTheme

//const val API_KEY = "1f013a3c1f144429b3d152935232502"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithabRepozitoriesTheme {
                val repolist = remember {
                    mutableStateOf(listOf<RepoModel>())
                }
                val nameRepo = remember {
                    mutableStateOf("")
                }
                getData(nameRepo.value, this, repolist)
                Image(
                    painter = BitmapPainter(ImageBitmap.imageResource(R.drawable.strokes_blue_brown_paint)),
                    contentDescription = "im1",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.FillBounds

                )
                Column {
                    MainScreen(this@MainActivity, repolist, nameRepo)
                }

            }
        }
    }
}

fun getData(name: String, ctx: Context,repolist: MutableState<List<RepoModel>>) {
    val list = ArrayList<RepoModel>()
    val url = "https://api.github.com/users/$name/repos"
    val queue = Volley.newRequestQueue(ctx)
    val sRequest = JsonArrayRequest(
        Request.Method.GET, url, null,
        { response ->
            try {
                for (i in 0 until response.length()) {
                    val resobj = response.getJSONObject(i)
                    list.add(
                        RepoModel(
                            resobj.getString("name")
                        )
                    )
                    repolist.value = list
                    Log.d("MyLog", "Response: ${resobj.getString("name")}")
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
