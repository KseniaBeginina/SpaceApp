package com.beginina.spaceapp.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beginina.spaceapp.R
import com.beginina.spaceapp.ui.theme.TransparentGray
import com.beginina.spaceapp.ui.theme.White

@Composable
fun IconButtonCustom(
    icon: Int = R.drawable.arrowright,
    onClick: () -> Unit
){
    IconButton(
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = TransparentGray,
            contentColor = White
        ),
        modifier = Modifier.size(40.dp),
        onClick = onClick
    ){
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}