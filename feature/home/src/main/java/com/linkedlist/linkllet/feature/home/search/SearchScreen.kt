package com.linkedlist.linkllet.feature.home.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.Search
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Gray100
import com.linkedlist.linkllet.core.designsystem.theme.Gray600
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LinkItem
import com.linkedlist.linkllet.core.ui.LnkAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState()

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
        ) {
            SearchBar(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 30.dp)
                    .padding(horizontal = 16.dp),
                value = uiState.keyword,
                onValueChange = viewModel::updateKeyword,
                search = viewModel::search,
            )

            when (state) {
                is SearchState.Loading -> { Text("데이터를 로드중입니다.") }
                is SearchState.BeforeToSearch -> {}
                is SearchState.Success -> {
                    LazyColumn {
                        item {
                            Text(
                                "링크 검색 결과 (${uiState.links.size})",
                                modifier = Modifier.padding(
                                    start = 24.dp,
                                    bottom = 20.dp
                                ),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                )
                            )
                        }
                        items(uiState.links.size) { index ->
                            val link = uiState.links[index]
                            LinkItem(
                                modifier = Modifier.padding(horizontal = 18.dp),
                                title = link.name,
                                link = link.url,
                                date = link.createAt,
                                hasMoreButton = false,
                            )
                        }
                    }
                }
                is SearchState.Empty -> {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(bottom = 70.dp), // 검색바 크기만큼 보정
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "검색 결과가 없습니다.",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(color = Gray600, fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                is SearchState.Error -> {
                    Text("뭔가 에러가 있나봐요.")
                }
            }
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
            modifier = Modifier
                .padding(end = 16.dp)
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