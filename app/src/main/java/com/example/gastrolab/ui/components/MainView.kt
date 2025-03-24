package com.example.gastrolab.ui.components

import android.R
import android.graphics.drawable.shapes.RoundRectShape
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainView(id: Int, title: String, text: String, image: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(5.dp)
            .clickable{
                navController.navigate("recipeScreen")
            }
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
    ) {
        Column(

            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop
            )
                Text(
                    textAlign = TextAlign.Center,
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onSecondary)
                )
        }
    }
}

@Composable
fun MainViewExCard(id:Int, title:String, text:String, image:Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable{
                navController.navigate("articleScreen")
            }
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 3.dp,
                color = MaterialTheme.colorScheme.onTertiary
            )

    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onTertiary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onTertiary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(100.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            shape = RectangleShape,
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    painter = painterResource(image),
                    contentDescription = "Food",
                    contentScale = ContentScale.Crop
                )

            }
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onTertiary)
                        .fillMaxSize(),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(MaterialTheme.colorScheme.onTertiary)
                    )
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.background,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.onBackground)
                            .fillMaxSize()
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MainViewSideCard(id:Int, title:String, text:String, image:Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable{
                navController.navigate("recipeScreen")
            }
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

@Composable
fun MainViewCompact(id: Int, title: String, text: String, image: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(5.dp)
            .clickable { navController.navigate("recipeScreen") }
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = text,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun MainViewExCardCompact(id: Int, title: String, text: String, image: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("articleScreen") }
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 3.dp,
                color = MaterialTheme.colorScheme.onTertiary
            )
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onTertiary)
                .padding(8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(2.dp, MaterialTheme.colorScheme.onBackground),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center


            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Text(
                    text = text,
                    textAlign = TextAlign.Start,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onBackground)
                        .fillMaxSize()
                        .padding(40.dp)
                )
            }
        }
    }
}

@Composable
fun MainViewSideCardCompact(id: Int, title: String, text: String, image: Int, navController: NavController) {
    Card(
        modifier = Modifier

            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navController.navigate("recipeScreen") }
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 3.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground),
                painter = painterResource(image),
                contentDescription = "Food",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = text,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
