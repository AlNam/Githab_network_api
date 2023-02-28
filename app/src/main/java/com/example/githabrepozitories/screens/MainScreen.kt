package com.example.githabrepozitories.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githabrepozitories.R
import com.example.githabrepozitories.getData
import com.example.githabrepozitories.ui.theme.Bluelight


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Image(
        painter = BitmapPainter(ImageBitmap.imageResource(R.drawable.strokes_blue_brown_paint)),
        contentDescription = "im1",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.5f),
        contentScale = ContentScale.FillBounds

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth(),
            backgroundColor = Bluelight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            displayListView()
        }
    }
}

@Composable
fun displayListView() {
    val context = LocalContext.current
    val repoList = remember {
        mutableStateListOf<String>()
    }
    getData(repoList, context)
    LazyColumn {
        items(repoList) {
            repo ->
            Text(text = repo, modifier = Modifier.padding(15.dp))
            Divider()
        }
    }
}
