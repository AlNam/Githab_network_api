package com.example.githabrepozitories.screens


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githabrepozitories.data.RepoModel
import com.example.githabrepozitories.ui.theme.Bluelight



@Composable
fun MainScreen(ctx: Context, repolist: MutableState<List<RepoModel>>,nameRepo: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Bluelight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = nameRepo.value,
                    textStyle = TextStyle(fontSize=25.sp),
                    onValueChange = {newText -> nameRepo.value = newText},
                    placeholder = { Text(text = "InputnameRepoGithab")}
                )
                InputItemCard(ctx, repolist)
            }
        }
    }
}

@Composable
fun InputItemCard(ctx:Context, repolist: MutableState<List<RepoModel>>) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        backgroundColor = Bluelight,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(
                repolist.value
            ) {index, item ->
                ListItem(item = item)
            }
        }
    }
}

@Composable
fun ListItem(item: RepoModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Bluelight),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text( modifier = Modifier.padding(8.dp,8.dp),
            text = item.repoName,
            style = TextStyle(fontSize = 15.sp))
        Divider(color = Color.Gray, modifier = Modifier
            .fillMaxWidth().padding(3.dp)
            .width(1.dp))
    }
}


