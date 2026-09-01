package com.cafelavado.app.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafelavado.app.components.CategoryChip
import com.cafelavado.app.components.MenuItemCard
import com.cafelavado.app.models.MenuCategory
import com.cafelavado.app.models.sampleMenuItems
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.TextPrimary

@Composable
fun MenuScreen() {
    var selectedCategory by remember { mutableStateOf<MenuCategory?>(null) }

    val filteredItems = if (selectedCategory == null) {
        sampleMenuItems
    } else {
        sampleMenuItems.filter { it.category == selectedCategory }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
    ) {
        Spacer(Modifier.height(24.dp))

        // ── Header ──────────────────────────────────────────────
        Text(
            text = "Cardápio",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )

        Spacer(Modifier.height(16.dp))

        // ── Category filter chips ───────────────────────────────
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
            // "Todos" chip
            item {
                CategoryChip(
                    label = "Todos",
                    selected = selectedCategory == null,
                    onClick = { selectedCategory = null },
                )
            }
            items(MenuCategory.entries.toList()) { category ->
                CategoryChip(
                    label = category.displayName,
                    selected = selectedCategory == category,
                    onClick = {
                        selectedCategory = if (selectedCategory == category) null else category
                    },
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Menu items list ─────────────────────────────────────
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
        ) {
            items(filteredItems, key = { it.id }) { item ->
                MenuItemCard(item = item)
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
