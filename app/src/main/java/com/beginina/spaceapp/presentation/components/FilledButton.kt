package com.beginina.spaceapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.beginina.spaceapp.ui.theme.Black
import com.beginina.spaceapp.ui.theme.White
import com.beginina.spaceapp.ui.theme.semibold24

@Composable
fun FilledButton(
    text: String,
    backgroundColor: Color = White,
    textColor: Color = Black,
    onClick: () -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        modifier = Modifier
            .height(54.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = { onClick() }
    ){
        Text(
            text = text,
            style = semibold24
        )
    }
}