package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// example of response from server
//{
//    "menu": [
//    {
//        "id": 1,
//        "title": "Spinach Artichoke Dip",
//        "price": "10"
//    },
//    ...
//    ]
//}


    @Serializable
data class MenuNetwork(
    // add code here
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    // add code here
    val id: Int,
    val title: String,
    val price: String
) {
    fun toMenuItemRoom() = MenuItemRoom(
        // add code here
        id = id,
        title = title,
        price = price.toDouble()
    )
}
