package com.example.gastrolab.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.clasetrabajo.data.viewmodel.RecipeViewModel
import com.example.gastrolab.R
import kotlin.Unit

@Composable
fun MainView(id: Int, title: String, description: String, imageURL: String,
             onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(5.dp)
            .clickable{
                onButtonClick()
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
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                model = imageURL,
                contentDescription = "",
                error = painterResource(R.drawable.generic),
                contentScale = ContentScale.Crop,
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
fun MainViewExCard(id: Int, title:String, description:String, imageURL:String,
                   onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable{
                onButtonClick()
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
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .size(100.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(
                            shape = RectangleShape,
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onBackground
                        ),
                    model = imageURL,
                    contentDescription = "",
                    error = painterResource(R.drawable.generic),
                    contentScale = ContentScale.Crop,
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
                        text = description,
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
fun MainViewSideCard(id:Int, title:String, description:String, imageURL:String,
                     onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable{
                onButtonClick()
            }
            .border(shape = RoundedCornerShape(10.dp),  width = 3.dp, color = MaterialTheme.colorScheme.onBackground)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 2.dp)
                    .width(120.dp)
                    .size(100.dp)
                    .border(shape = RectangleShape,  width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
                    .height(200.dp),
                model = imageURL,
                contentDescription = "",
                error = painterResource(R.drawable.generic),
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
                    text = description,
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
fun MainViewCompact(id: Int, title: String, description: String, imageURL: String,
                    onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(5.dp)
            .clickable { onButtonClick() }
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
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground),
                model = imageURL,
                contentDescription = "",
                error = painterResource(R.drawable.generic),
                contentScale = ContentScale.Crop,
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
                    text = description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun MainViewExCardCompact(id: Int, title: String, text: String, imageURL: String,
  onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {onButtonClick()}
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
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(2.dp, MaterialTheme.colorScheme.onBackground),
                model = imageURL,
                contentDescription = "",
                error = painterResource(R.drawable.generic),
                contentScale = ContentScale.Crop,
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
fun MainViewSideCardCompact(id: Int, title: String, description: String, imageURL: String, onButtonClick: () -> Unit, viewModel: RecipeViewModel = viewModel()) {
    Card(
        modifier = Modifier

            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onButtonClick()}
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
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, MaterialTheme.colorScheme.onBackground),
                model = imageURL,
                contentDescription = "",
                error = painterResource(R.drawable.generic),
                contentScale = ContentScale.Crop,
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
                    text = description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
