package com.linkedlist.linkllet.core.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.linkedlist.linkllet.core.designsystem.theme.Gray600
import com.linkedlist.linkllet.core.designsystem.theme.Gray200
import com.linkedlist.linkllet.core.designsystem.theme.Typography

@Composable
fun LinkItem(
    modifier: Modifier = Modifier,
    title: String,
    link: String,
    date: String,
    onDelete: () -> Unit = {}
) {
    var dropdownState by remember { mutableStateOf(false) }
    val context = LocalContext.current
    OutlinedCard(
        modifier = modifier
            .wrapContentHeight()
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, color = Gray200)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight().clickable {
                    try {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(link)
                            )
                        )
                    }catch (e: Exception){
                        Log.d("LinkCardClick","${e}")
                    }
                }
        ) {
            val (moreRefs, titleRefs, linkRefs, dateRefs) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleRefs) {
                        top.linkTo(parent.top, 20.dp)
                        end.linkTo(moreRefs.start, 70.dp)
                        start.linkTo(parent.start, 24.dp)
                        width = Dimension.fillToConstraints
                    },
                text = title,
                style = Typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .constrainAs(linkRefs) {
                        top.linkTo(titleRefs.bottom, 3.dp)
                        end.linkTo(titleRefs.end)
                        start.linkTo(titleRefs.start)
                        width = Dimension.fillToConstraints
                    },
                text = link,
                style = Typography.bodySmall,
                color = Gray600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateRefs) {
                        top.linkTo(linkRefs.bottom, 18.dp)
                        end.linkTo(parent.end, 8.dp)
                        start.linkTo(titleRefs.start)
                        bottom.linkTo(parent.bottom, 20.dp)
                        width = Dimension.fillToConstraints
                    },
                text = "저장일 | ${date}",
                style = Typography.bodyMedium
            )


            LnkIconButton(
                modifier = Modifier
                    .constrainAs(moreRefs) {
                        end.linkTo(parent.end, 8.dp)
                        top.linkTo(parent.top, 12.dp)
                    },
                innerPadding = 8.dp,
                onClick = {
                    dropdownState = !dropdownState
                }
            ) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)
                DropdownMenu(
                    modifier = Modifier.background(Color.White),
                    expanded = dropdownState,
                    onDismissRequest = { dropdownState = !dropdownState }) {
                    DropdownMenuItem(text = {
                        Text(text = "링크 삭제하기")
                    }, onClick = {
                        onDelete()
                        dropdownState = !dropdownState
                    })
                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun LinkItemPreview() {
    MaterialTheme {
        LinkItem(
            modifier = Modifier.fillMaxWidth(),
            title = "테스트",
            link = "https://linkllet.io",
            date = "2023.06"
        )
    }
}