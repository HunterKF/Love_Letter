package com.example.loveletter.presentation.game.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loveletter.domain.CardAvatar
import com.example.loveletter.ui.theme.Navy
import com.example.loveletter.ui.theme.Steel

@Composable
fun MenuItem(cardAvatar: CardAvatar, modifier: Modifier, textColor: Color = Color.White) {

    Box(modifier = modifier
        .clip(RoundedCornerShape(50.dp))
        .background(Navy)
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 26.dp))
        {

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${cardAvatar.number} - ${cardAvatar.cardName}",
                    style = MaterialTheme.typography.h6,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    "in game: x${cardAvatar.numberInGame}",
                    style = MaterialTheme.typography.subtitle1,
                    color = textColor,
                    fontSize = 12.sp

                )
            }
            Text(
                text = stringResource(id = cardAvatar.ruleShortDescription),
                style = MaterialTheme.typography.body1,
                color = textColor,
                fontSize = 12.sp


            )

        }
    }

}