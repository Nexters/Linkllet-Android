package com.linkedlist.linkllet.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.linkedlist.linkllet.core.designsystem.icon.LnkIcon
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.O
import com.linkedlist.linkllet.core.designsystem.icon.lnkicon.X
import com.linkedlist.linkllet.core.designsystem.theme.Gray200
import com.linkedlist.linkllet.core.designsystem.theme.Typography

@Composable
fun LnkDialog(
    text : String,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onOk : () -> Unit = {},
    onCancel : () -> Unit = {}
) {
    if (visible) {
        CustomAlertDialog(
            onDismissRequest = onDismissRequest
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.background(Color.White).padding(12.dp).wrapContentSize()
                ) {
                    Box(modifier = Modifier.height(108.dp).fillMaxWidth()){
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text= text,
                            maxLines = 3,
                            style = Typography.bodySmall,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                    Divider(modifier = Modifier.fillMaxWidth(), color = Gray200)
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        LnkIconButton(
                            modifier = Modifier.align(Alignment.CenterStart),
                            onClick = {
                                onCancel()
                                onDismissRequest()
                            }
                        ) {
                            Icon(imageVector = LnkIcon.X, contentDescription = null)

                        }

                        LnkIconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = {
                                onOk()
                                onDismissRequest()
                            }
                        ) {
                            Icon(imageVector = LnkIcon.O, contentDescription = null)
                        }
                    }
                }

            }
        }
    }

}


@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        content()
    }
}