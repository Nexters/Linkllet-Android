package com.linkedlist.linkllet.feature.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linkedlist.linkllet.core.designsystem.R
import com.linkedlist.linkllet.core.designsystem.theme.Gray100
import com.linkedlist.linkllet.core.designsystem.theme.Gray600
import com.linkedlist.linkllet.core.designsystem.theme.Typography

@Composable
fun LoginScreen() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if(maxHeight < 400.dp){ // 가로 회전 대응
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .width(164.dp)
                        .height(122.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고"
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "소셜 계정으로 간편 가입하기",
                    style = Typography.bodySmall,
                    color = Gray600
                )

                Spacer(modifier = Modifier.size(10.dp))
                Card(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier.clickable {

                        }
                    ){
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(id = R.drawable.btn_kakao),
                            contentDescription = "카카오 로그인"
                        )
                    }
                }
                Spacer(modifier = Modifier.size(30.dp))
                LoginSkipButton(
                    onClick = {

                    }
                )
            }

        }else {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .width(164.dp)
                        .height(122.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "로고"
                )
                Spacer(modifier = Modifier.size(40.dp))
                Text(
                    text = "소셜 계정으로 간편 가입하기",
                    style = Typography.bodySmall,
                    color = Gray600
                )

                Spacer(modifier = Modifier.size(20.dp))
                Card(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier.clickable {

                        }
                    ){
                        Image(
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(id = R.drawable.btn_kakao),
                            contentDescription = "카카오 로그인"
                        )
                    }
                }

            }
            LoginSkipButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp),
                onClick = {

                }
            )
        }


    }
}

@Composable
fun LoginSkipButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
){
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(width = 1.dp, color = Gray100),
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "로그인 건너뛰기",
                style = Typography.bodySmall,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}