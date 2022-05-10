package com.githubhub.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryText(
    text: String,
    modifier: Modifier = Modifier,
    textSize: TextUnit = 24.sp
) {
    Text(
        text = text,
        fontSize = textSize,
        fontWeight = FontWeight.W400,
        modifier = modifier
    )
}