package com.example.gastrolab.ui.components

import android.graphics.drawable.shapes.RoundRectShape
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainView(id:Int, title:String, text:String, image:Int){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onSurface)

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            painter = painterResource(image),
            contentDescription = "Food",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onSurface)

        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left,
                lineHeight = 12.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onSurface)

        ) {
            Text(
                //text = stringResource(R.string.lorem),
                text = text,
                textAlign = TextAlign.Justify,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(10.dp)
                    .fillMaxSize()

            )
        }
    }
}
@Composable
fun MainViewExCard(id:Int, title:String, text:String, image:Int){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onSurface)

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            painter = painterResource(image),
            contentDescription = "Food",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onSurface)

        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left,
                lineHeight = 12.sp,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onSurface)

        ) {
            Text(
                //text = stringResource(R.string.lorem),
                text = text,
                textAlign = TextAlign.Justify,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(10.dp)
                    .fillMaxSize()

            )
        }
    }
}
@Composable
fun CompactMainView(id:Int, title:String, text:String, image:Int) {
    Card(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Row() {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .size(100.dp)
                    .height(200.dp),
                painter = painterResource(image),
                contentDescription = "Audi TTS",
                contentScale = ContentScale.Crop,

                )

            Column() {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = text,
                    textAlign = TextAlign.Left,
                    lineHeight = 14.sp,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}