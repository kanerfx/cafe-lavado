package com.cafelavado.app.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// ============================================================
//  MenuItem
//  ------------------------------------------------------------
//  Firestore-ready: @Serializable + default values for every field,
//  so kotlinx-serialization can hydrate documents that are missing
//  optional fields (e.g. when a new field is added later).
// ============================================================

@Serializable
data class MenuItem(
    @SerialName("id")              val id: String = "",
    @SerialName("name")            val name: String = "",
    @SerialName("description")     val description: String = "",
    @SerialName("price_reais")     val priceReais: Double = 0.0,
    @SerialName("category")        val category: MenuCategory = MenuCategory.CAFES_QUENTES,
    @SerialName("image_url")       val imageUrl: String? = null,
    @SerialName("emoji")           val emoji: String = "☕",
    @SerialName("available")       val available: Boolean = true,
    @SerialName("sort_order")      val sortOrder: Int = 0,
)

@Serializable
enum class MenuCategory(val displayName: String) {
    @SerialName("CAFES_QUENTES")  CAFES_QUENTES("Cafés Quentes"),
    @SerialName("BEBIDAS_GELADAS") BEBIDAS_GELADAS("Bebidas Geladas"),
    @SerialName("DOCES_SALGADOS")  DOCES_SALGADOS("Doces & Salgados"),
}

// Sample data — used by SampleDataRepository until Firestore is plugged in.
val sampleMenuItems = listOf(
    MenuItem("1",  "Café Expresso",   "Café puro e encorpado",                 7.50,  MenuCategory.CAFES_QUENTES,  emoji = "☕"),
    MenuItem("2",  "Café com Leite",  "Espresso com leite vaporizado",         9.00,  MenuCategory.CAFES_QUENTES,  emoji = "🥛"),
    MenuItem("3",  "Cappuccino",      "Espresso, leite vaporizado e espuma",  12.00,  MenuCategory.CAFES_QUENTES,  emoji = "☕"),
    MenuItem("4",  "Mocha",           "Espresso com chocolate e leite",       13.00,  MenuCategory.CAFES_QUENTES,  emoji = "🍫"),
    MenuItem("5",  "Cold Brew",       "Café extraído a frio por 12 horas",    14.00,  MenuCategory.BEBIDAS_GELADAS, emoji = "🧊"),
    MenuItem("6",  "Café Gelado",     "Espresso com gelo e leite",            11.00,  MenuCategory.BEBIDAS_GELADAS, emoji = "❄️"),
    MenuItem("7",  "Frappuccino",     "Café gelado batido com gelo",          15.00,  MenuCategory.BEBIDAS_GELADAS, emoji = "🥤"),
    MenuItem("8",  "Pão de Queijo",   "Tradicional mineiro, quentinho",        5.00,  MenuCategory.DOCES_SALGADOS, emoji = "🧀"),
    MenuItem("9",  "Coxinha",         "Recheada com frango desfiado",          7.00,  MenuCategory.DOCES_SALGADOS, emoji = "🍗"),
    MenuItem("10", "Bolo de Cenoura", "Com cobertura de chocolate",            9.00,  MenuCategory.DOCES_SALGADOS, emoji = "🍰"),
    MenuItem("11", "Pastel de Nata",  "Massa folhada com creme",               8.00,  MenuCategory.DOCES_SALGADOS, emoji = "🥧"),
)
