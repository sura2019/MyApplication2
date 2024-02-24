package com.example.myapplication.views.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(SchoolRepository(RetrofitInstance.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SchoolList(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun SchoolList(viewModel: SchoolViewModel) {
    val schools by viewModel.schools.collectAsState()
    Column {
        Text(text = "School List Size: ${schools.size}", modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        SchoolItems(schools = schools)
    }
}


@Composable
fun SchoolItems(schools: List<School>) {
    Column {
        schools.forEach { school ->
            var expanded by remember { mutableStateOf(false) }
            SchoolItem(school = school, expanded = expanded) {
                expanded = !expanded
            }
            Divider()
        }
    }
}

@Composable
fun SchoolItem(school: School, expanded: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Text(text = school.schoolName, style = MaterialTheme.typography.h6)
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = school.overviewParagraph, style = MaterialTheme.typography.body1)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSchoolList() {
    MyApplicationTheme {
        SchoolList(viewModel = SchoolViewModel(SchoolRepository(RetrofitInstance.apiService)))
    }
}