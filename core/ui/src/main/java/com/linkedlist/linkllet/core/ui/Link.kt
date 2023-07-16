package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun LinkItem(
    modifier: Modifier = Modifier,
    title: String,
    link : String,
    date : String
) {
    Card(
        modifier = modifier.wrapContentHeight()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (moreRefs, titleRefs, linkRefs, dateRefs) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(titleRefs) {
                        top.linkTo(parent.top, 20.dp)
                        end.linkTo(moreRefs.start)
                        start.linkTo(parent.start, 24.dp)
                        width = Dimension.fillToConstraints
                    },
                text = title
            )

            Text(
                modifier = Modifier
                    .constrainAs(linkRefs) {
                        top.linkTo(titleRefs.bottom, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                        start.linkTo(titleRefs.start)
                        width = Dimension.fillToConstraints
                    },
                text = link
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
                text = "저장일 | ${date}"
            )


            LnkIconButton(
                modifier = Modifier
                    .constrainAs(moreRefs) {
                        end.linkTo(parent.end, 8.dp)
                        top.linkTo(parent.top, 12.dp)
                    },
                innerPadding = 8.dp,
                onClick = {

                }
            ) {
                Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = null)
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