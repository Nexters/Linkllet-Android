package com.linkedlist.linkllet.feature.home.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Search
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Gray100
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LnkAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            LnkAppBar(
                modifier = Modifier.shadow(elevation = 4.dp), // fixme : 임시 그림자
                title = { AppBarTitle() },
                action = { CloseAction(onBack = onBack) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            SearchBar(
                modifier = Modifier.padding(top = 20.dp),
                value = uiState.keyword,
                onValueChange = viewModel::updateKeyword,
                search = {},
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    search: () -> Unit,
) {
    Box(
        modifier = modifier.height(50.dp),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Surface(
            Modifier.fillMaxHeight(),
            shape = RoundedCornerShape(12.dp),
            color = Gray100,
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 54.dp),
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        innerTextField()
                    }
                }
            )
        }
        Icon(
            modifier = Modifier.padding(end = 16.dp)
                .clickable { search() },
            imageVector = LnkIcon.Search,
            contentDescription = "검색",
        )
    }
}

@Composable
fun AppBarTitle() {
    Text("검색", style = Typography.titleMedium, fontWeight = FontWeight.Bold)
}

@Composable
fun CloseAction(onBack: () -> Unit) {
    Icon(
        modifier = Modifier.clickable { onBack() },
        imageVector = LnkIcon.X, contentDescription = "닫기"
    )
}