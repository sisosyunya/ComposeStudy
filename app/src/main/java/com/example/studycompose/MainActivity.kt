package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocalReadScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studycompose.ui.theme.StudyComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyComposeTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val todoList =(1..100).map { "タスク${it}" }
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var todoList:SnapshotStateList<String> by remember { mutableStateOf(mutableStateListOf()) }
    var text: String by remember { mutableStateOf("") }
    Column {
        CenterAlignedTopAppBar(
            title = { Text("Todo List") }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            items(todoList){todo ->
                Text(text = todo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
            label = { Text("todo")},
            modifier = Modifier.wrapContentHeight().weight(1f))
            Spacer(Modifier.size(16.dp))
            Button(onClick = {
                if (text.isEmpty()) return@Button
                todoList.add(text)
                text=""
            },
            modifier = Modifier.align(Alignment.CenterVertically)) {
                Text("add")
            }
        }
    }

    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StudyComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            val todoList = (1..100).map { "タスク${it}" }
            MainScreen()
        }
    }
}