package com.example.gastrolab.ui.components

import android.R
import android.graphics.drawable.shapes.RoundRectShape
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.TextStyle
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
import androidx.compose.ui.graphics.Brush
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
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onBackground)

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
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onSecondary)

        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left,
                lineHeight = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)

        ) {
            Text(
                //text = stringResource(R.string.lorem),
                text = text,
                textAlign = TextAlign.Justify,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
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
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onBackground)

    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            painter = painterResource(image),
            contentDescription = "Food",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onTertiary)

        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left,
                lineHeight = 12.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)

        ) {
            Text(
                text = text,
                textAlign = TextAlign.Justify,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(10.dp)
                    .fillMaxSize()

            )
        }
    }
}
@Composable
fun MainViewSideCard(id:Int, title:String, text:String, image:Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onBackground)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 2.dp)
                    .width(100.dp)
                    .size(100.dp)
                    .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
                    .height(200.dp),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop,

                )

            Column(
                modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(5.dp)

                )
                Text(
                    text = text,
                    textAlign = TextAlign.Justify,
                    lineHeight = 12.sp,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(5.dp)
                )

            }
        }
    }
}

@Composable
fun MainViewSideCardCompact(id:Int, title:String, text:String, image:Int) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onBackground)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 2.dp)
                    .width(120.dp)
                    .size(100.dp)
                    .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
                    .height(200.dp),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop,

                )

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(5.dp)

                )
                Text(
                    text = text,
                    textAlign = TextAlign.Justify,
                    lineHeight = 12.sp,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
        }
    }
}