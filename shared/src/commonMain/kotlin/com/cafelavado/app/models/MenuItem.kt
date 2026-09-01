package com.cafelavado.app.models

/**
 * Represents a single item on the café menu.
 */
data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val priceReais: Double,
    val category: MenuCategory,
)

enum class MenuCategory(val displayName: String) {
    CAFES_QUENTES("Cafés Quentes"),
    BEBIDAS_GELADAS("Bebidas Geladas"),
    DOCES_SALGADOS("Doces & Salgados"),
}

/** Placeholder data for the menu foundation. */
val sampleMenuItems = listOf(
    MenuItem("1", "Café Expresso",   "Café puro e encorpado",                7.50,  MenuCategory.CAFES_QUENTES),
    MenuItem("2", "Café com Leite",  "Espresso com leite vaporizado",        9.00,  MenuCategory.CAFES_QUENTES),
    MenuItem("3", "Cappuccino",      "Espresso, leite vaporizado e espuma", 12.00,  MenuCategory.CAFES_QUENTES),
    MenuItem("4", "Mocha",           "Espresso com chocolate e leite",      13.00,  MenuCategory.CAFES_QUENTES),
    MenuItem("5", "Cold Brew",       "Café extraído a frio por 12 horas",   14.00,  MenuCategory.BEBIDAS_GELADAS),
    MenuItem("6", "Café Gelado",     "Espresso com gelo e leite",           11.00,  MenuCategory.BEBIDAS_GELADAS),
    MenuItem("7", "Frappuccino",     "Café gelado batido com gelo",         15.00,  MenuCategory.BEBIDAS_GELADAS),
    MenuItem("8", "Pão de Queijo",   "Tradicional mineiro, quentinho",       5.00,  MenuCategory.DOCES_SALGADOS),
    MenuItem("9", "Coxinha",         "Recheada com frango desfiado",         7.00,  MenuCategory.DOCES_SALGADOS),
    MenuItem("10","Bolo de Cenoura", "Com cobertura de chocolate",           9.00,  MenuCategory.DOCES_SALGADOS),
    MenuItem("11","Pastel de Nata",  "Massa folhada com creme",              8.00,  MenuCategory.DOCES_SALGADOS),
)
