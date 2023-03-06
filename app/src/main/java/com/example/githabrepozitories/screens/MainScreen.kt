package com.example.githabrepozitories.screens


import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Bluelight,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(
                repolist.value
                /*listOf(
                    RepoModel("repoName"),
                    RepoModel("repoName")
                )*/
            ) {index, item ->
                ListItem(item = item)
            }
        }
    }
}

@Composable
fun ListItem(item: RepoModel) {
    Text(text = item.repoName)
}


