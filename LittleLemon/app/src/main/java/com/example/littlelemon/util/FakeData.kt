package com.example.littlelemon.util

import com.example.littlelemon.R

data class MenuCategory(val name: String)
data class MenuItem(val name: String,
                    val description: String,
                    val price: Double,
                    var image: Int,
                    val category: MenuCategory)

val startersCategory = MenuCategory("Starters")
val mainsCategory = MenuCategory("Mains")
val saladsCategory = MenuCategory("Salads")
val dessertsCategory = MenuCategory("Desserts")
val drinksCategory = MenuCategory("Drinks")

// create a list of menu items
val desserts = listOf(
    MenuItem(
        name = "Chocolate Cake",
        description = "Rich and decadent chocolate cake served with a scoop of vanilla ice cream.",
        price = 9.99,
        image = 0,
        category = dessertsCategory
    ),
    MenuItem(
        name = "Cheesecake",
        description = "Creamy cheesecake with a graham cracker crust and topped with fresh berries.",
        price = 8.99,
        image = 0,
        category = dessertsCategory
    ),
    MenuItem(
        name = "Tiramisu",
        description = "Classic Italian dessert with layers of espresso-soaked ladyfingers, mascarpone cream, and cocoa.",
        price = 10.99,
        image = 0,
        category = dessertsCategory
    ),
    MenuItem(
        name = "Creme Brulee",
        description = "Silky smooth vanilla custard with a caramelized sugar crust.",
        price = 7.99,
        image = 0,
        category = dessertsCategory
    ),
    MenuItem(
        name = "Chocolate Cake",
        description = "Rich and decadent chocolate cake served with a scoop of vanilla ice cream.",
        price = 9.99,
        image = 0,
        category = dessertsCategory
    ),
)

// starters that are not salad
val starters = listOf(
    MenuItem(
        name = "Bruschetta",
        description = "Grilled bread garlic, tomatoes, olive oil.",
        price = 6.99,
        image = 0,
        category = startersCategory
    ),
    MenuItem(
        name = "Garlic Bread",
        description = "Grilled bread with garlic, olive oil, and salt.",
        price = 4.99,
        image = 0,
        category = startersCategory
    ),
    MenuItem(
        name = "Caprese Salad",
        description = "Fresh mozzarella, tomatoes, and basil drizzled with balsamic glaze.",
        price = 8.99,
        image = 0,
        category = startersCategory
    ),
    MenuItem(
        name = "Stuffed Mushrooms",
        description = "Baked mushroom caps stuffed with feta cheese and herbs.",
        price = 7.99,
        image = 0,
        category = startersCategory
    ),
    MenuItem(
        name = "Bruschetta",
        description = "Grilled bread garlic, tomatoes, olive oil.",
        price = 6.99,
        image = 0,
        category = startersCategory
    ),
)


val mains = listOf(
    MenuItem(
        name = "Steak",
        description = "Juicy grilled steak with your choice of French fries, baked potato, or mashed potatoes.",
        price = 19.99,
        image = 0,
        category = mainsCategory
    ),
    MenuItem(
        name = "Spaghetti and Meatballs",
        description = "Juicy meatballs with tomato sauce and spaghetti.",
        price = 14.99,
        image = 0,
        category = mainsCategory
    ),
    MenuItem(
        name = "Chicken Parmesan",
        description = "Fried chicken breast topped with tomato sauce and mozzarella cheese.",
        price = 17.99,
        image = 0,
        category = mainsCategory
    ),
    MenuItem(
        name = "Chicken Alfredo",
        description = "Pasta with chicken in a creamy alfredo sauce.",
        price = 16.99,
        image = 0,
        category = mainsCategory
    ),
    MenuItem(
        name = "Steak",
        description = "Juicy grilled steak with your choice of French fries, baked potato, or mashed potatoes.",
        price = 19.99,
        image = 0,
        category = mainsCategory
    ),
)

val drinks = listOf(
    MenuItem(
        name = "Coke",
        description = "Coca-Cola is a carbonated soft drink produced by The Coca-Cola Company.",
        price = 2.99,
        image = 0,
        category = drinksCategory
    ),
    MenuItem(
        name = "Sprite",
        description = "Sprite is a colorless, caffeine-free, lemon and lime-flavored soft drink.",
        price = 2.99,
        image = 0,
        category = drinksCategory
    ),
    MenuItem(
        name = "Fanta",
        description = "Fanta is a brand of fruit-flavored carbonated soft drinks created by Coca-Cola Deutschland.",
        price = 2.99,
        image = 0,
        category = drinksCategory
    ),
    MenuItem(
        name = "Lemonade",
        description = "Lemonade is any of various sweetened beverages found around the world, all characterized by lemon flavor.",
        price = 2.99,
        image = 0,
        category = drinksCategory
    ),
    MenuItem(
        name = "Coke",
        description = "Coca-Cola is a carbonated soft drink produced by The Coca-Cola Company.",
        price = 2.99,
        image = 0,
        category = drinksCategory
    ),
)

val salads = listOf(
    MenuItem(
        name = "Caesar Salad",
        description = "Crisp romaine lettuce with parmesan cheese, croutons, and a creamy Caesar dressing.",
        price = 8.99,
        image = 0,
        category = saladsCategory
    ),
    MenuItem(
        name = "Caprese Salad",
        description = "Fresh mozzarella, tomatoes, and basil drizzled with a balsamic reduction.",
        price = 7.99,
        image = 0,
        category = saladsCategory
    ),
    MenuItem(
        name = "Greek Salad",
        description = "Chopped tomatoes, cucumbers, peppers, onions, olives, and feta cheese with a red wine vinaigrette.",
        price = 9.99,
        image = 0,
        category = saladsCategory
    ),
    MenuItem(
        name = "House Salad",
        description = "Mixed greens with tomatoes, onions, and carrots served with your choice of dressing.",
        price = 6.99,
        image = 0,
        category = saladsCategory
    ),
    MenuItem(
        name = "Caesar Salad",
        description = "Crisp romaine lettuce with parmesan cheese, croutons, and a creamy Caesar dressing.",
        price = 8.99,
        image = 0,
        category = saladsCategory
    ),
)

val allCategories = listOf(
    startersCategory,
    saladsCategory,
    mainsCategory,
    dessertsCategory,
    drinksCategory,
)


fun getAllMenuItems(category: String, query: String): List<MenuItem> {
    val items = arrayListOf<MenuItem>().apply {
        addAll(starters.map { it.image = R.drawable.bruschetta; it })
        addAll(mains.map { it.image = R.drawable.grilled_fish; it })
        addAll(salads.map { it.image = R.drawable.greek_salad; it })
        addAll(desserts.map { it.image = R.drawable.lemon_dessert; it })
        addAll(drinks.map { it.image = R.drawable.water_bottle; it })
    }

    return if (query.isEmpty()) {
        items.filter { it.category.name == category }
    } else {
        items.filter { it.name.contains(query, true) || it.description.contains(query, true) }
    }
}


