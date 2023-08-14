package com.linkedlist.linkllet.feature.home.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.WebContent
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewNavigator
import com.linkedlist.linkllet.core.data.model.Link
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Gray600
import com.linkedlist.linkllet.core.designsystem.theme.Typography
import com.linkedlist.linkllet.core.ui.LinkItem
import com.linkedlist.linkllet.core.ui.LnkAppBar
import com.linkedlist.linkllet.core.ui.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val state by viewModel.state.collectAsState()

    val webViewClient = AccompanistWebChromeClient()
    val webViewNavigator = rememberWebViewNavigator()
    var webViewState by remember {
        mutableStateOf<WebViewState?>(null)
    }

    BackHandler {
        when {
            webViewNavigator.canGoBack -> webViewNavigator.navigateBack()
            webViewState != null -> webViewState = null
            else -> onBack()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.eventsFlow.collect { event ->
            when (event) {
                is SearchEvent.ShowLink -> {
                    webViewState = WebViewState(WebContent.Url(event.link))
                }
            }
        }
    }

    Box {
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
                    is SearchState.Loading -> {
                        Text("데이터를 로드중입니다.")
                    }

                    is SearchState.BeforeToSearch -> {}
                    is SearchState.Success -> { SearchResult(links = uiState.links, onLinkItemClicked = viewModel::showLink) }
                    is SearchState.Empty -> { Empty() }
                    is SearchState.Error -> {
                        Text("뭔가 에러가 있나봐요.")
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = webViewState != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            webViewState?.let {
                WebView(
                    state = it,
                    chromeClient = webViewClient,
                    navigator = webViewNavigator
                )
            }
        }
    }
}

@Composable
fun SearchResult(links: List<Link>, onLinkItemClicked: (link: String) -> () -> Unit) {
    LazyColumn {
        item {
            Text(
                "링크 검색 결과 (${links.size})",
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
        items(links.size) { index ->
            val link = links[index]
            LinkItem(
                modifier = Modifier.padding(horizontal = 18.dp),
                title = link.name,
                link = link.url,
                date = link.createAt,
                onClick = onLinkItemClicked(link.url),
                hasMoreButton = false,
            )
        }
    }
}

@Composable
fun Empty() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 70.dp), // 화면 가운데 정렬을 위해 검색바가 차지하는 크기만큼 보정
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