package com.example.littlelemon.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.Olive
import com.example.littlelemon.ui.theme.White
import com.example.littlelemon.ui.theme.Yellow
import com.example.littlelemon.util.AppDestinations
import com.example.littlelemon.util.MenuItem
import com.example.littlelemon.util.allCategories
import com.example.littlelemon.util.getAllMenuItems
import com.example.littlelemon.util.mains

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Header(navController)


        var selectedCategory = remember { mutableStateOf("Starters") }
        var menuItems = remember { mutableStateOf(getAllMenuItems("Starters", "")) }
        var searchQuery = remember { mutableStateOf("") }

        Hero(selectedCategory, menuItems, searchQuery)
        Categories(selectedCategory, menuItems, searchQuery)
        Menu(selectedCategory, menuItems, searchQuery)
    }
}

@Composable
private fun Header(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(60.dp)
                .align(Alignment.Center)

        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(70.dp)
                .clip(shape = RoundedCornerShape(50))
                .clickable(onClick = {
                    navController.navigate(AppDestinations.profile)
                })
                .align(Alignment.CenterEnd)

        )
    }

}

@Composable
private fun Hero(selectedCategory: MutableState<String>,
                 menuItems: MutableState<List<MenuItem>>,
                 searchQuery: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Olive)
            .padding(10.dp)
    ) {
        Column() {
            Text(text = "Little Lemon", fontSize = 40.sp, color = Yellow)
            Text(text = "Chicago", fontSize = 30.sp, color = White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a\n" +
                    "modern twist.",
            modifier = Modifier
                .weight(1f)
            ,
            color = White)
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero",
                modifier = Modifier
                    .size(100.dp)

            )

        }
        SearchBar(selectedCategory, menuItems, searchQuery)


    }

}

@Composable
private fun SearchBar(selectedCategory: MutableState<String>,
                      menuItems: MutableState<List<MenuItem>>,
                      searchQuery: MutableState<String>) {

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Yellow,
        unfocusedBorderColor = LightGray,
        textColor = Color.Black,
        backgroundColor = White,
    )

    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it
            menuItems.value = getAllMenuItems(selectedCategory.value, searchQuery.value)
        },
        label = {Text(text = "Enter Search Phrase")},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 15.dp),
        colors = textFieldColors,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription ="Search Icon",
                tint = Color.Gray
            )
        }
    )
}

@Composable
private fun Categories(selectedCategory: MutableState<String>,
                       menuItems: MutableState<List<MenuItem>>,
                       searchQuery: MutableState<String>
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "ORDER FOR DELIVERY", fontSize = 20.sp, color = Color.Black)

        val state = rememberScrollState()
        LaunchedEffect(Unit) { state.animateScrollTo(0) }

        Row (
            modifier = Modifier.horizontalScroll(state)
                ) {

            allCategories.forEach { category ->
                Spacer(modifier = Modifier.width(16.dp))
                PillButton(onClick = {
                    selectedCategory.value = category.name
                    menuItems.value = getAllMenuItems(category.name, searchQuery.value)
                }, buttonText = category.name)
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray)
            .padding(vertical = 10.dp)
        )
    }

}

@Composable
fun PillButton(
    onClick: () -> Unit,
    buttonText: String
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large.copy(
            bottomStart = CornerSize(percent = 50),
            bottomEnd = CornerSize(percent = 50),
            topStart = CornerSize(percent = 50),
            topEnd = CornerSize(percent = 50)
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.LightGray,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buttonText,
                style = MaterialTheme.typography.button
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}


@Composable
private fun Menu(selectedCategory: MutableState<String>,
                 menuItems: MutableState<List<MenuItem>>,
                 searchQuery: MutableState<String>
) {
    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(0) }

    Column (
        modifier = Modifier.verticalScroll(state)
    ) {
        menuItems.value.forEach { menuItem ->
            MenuItemView(menuItem = menuItem)
        }
    }
}

@Composable
private fun MenuItemView(menuItem: MenuItem) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            ) {
        Column (
            modifier = Modifier.weight(1f)
                ) {
            Text(text = menuItem.name, fontSize = 20.sp, color = Color.Black)
            Text(text = menuItem.description, fontSize = 15.sp, color = Color.Gray)
            Text(text = "$${menuItem.price}", fontSize = 15.sp, color = Color.Black)
        }

        Image(
            painter = painterResource(id = menuItem.image),
            contentDescription = "Menu Item Image",
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)

        )
    }

}

@Preview
@Composable
public fun ShowHome() {
    HomeScreen(navController = rememberNavController())
}
