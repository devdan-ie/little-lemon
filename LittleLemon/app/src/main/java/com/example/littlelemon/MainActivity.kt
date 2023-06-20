package com.example.littlelemon

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // add databaseMenuItems code here
                val databaseMenuItems = database.menuItemDao().getAll().observeAsState(emptyList())

                // add orderMenuItems variable here
                val orderMenuItems = remember { mutableStateOf(false) }


                // add menuItems variable here
                var menuItems = if (orderMenuItems.value) {
                    databaseMenuItems.value.sortedBy { it.title }
                } else {
                    databaseMenuItems.value
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(50.dp)
                    )



                    // add Button code here
                    Button(onClick = {
                        orderMenuItems.value = true
                     },
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp, start = 50.dp, end = 50.dp)) {
                        Text(text = "Tap to Order by Name")
                    }


                    // add searchPhrase variable here
                    val searchPhrase = remember { mutableStateOf("") }

                    // Add OutlinedTextField
                    OutlinedTextField(
                        value = searchPhrase.value,
                        onValueChange = { searchPhrase.value = it },
                        label = { Text("Search") },
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp, start = 50.dp, end = 50.dp)
                    )

                    // filter menuItems here
                    menuItems = menuItems.filter {
                          it.title.contains(searchPhrase.value, ignoreCase = true)
                      }

                    // add is not empty check here
                    if (menuItems.isNotEmpty()) {
                        MenuItemsList( items = menuItems)
                    } else {
                        Text(
                            text = "No menu items found",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }


        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                // add code here
                val items = fetchMenu()
                saveMenuToDatabase(items)
            }
        }
    }



    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json
        try {
            val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
            val response: HttpResponse = httpClient.get(url)
            val menuNetwork = kotlinx.serialization.json.Json.decodeFromString(MenuNetwork.serializer(), response.bodyAsText())
            return menuNetwork.menu
        } catch (e: Exception) {
            throw Exception("Failed to fetch menu. ${e.message}")
        } finally {
            httpClient.close()
        }
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(menuItem.title)
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Right,
                        text = "%.2f".format(menuItem.price)
                    )
                }
            }
        )
    }
}
