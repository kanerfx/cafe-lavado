package com.cafelavado.app.screens.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cafelavado.app.components.CategoryChip
import com.cafelavado.app.components.MenuItemCard
import com.cafelavado.app.models.MenuCategory
import com.cafelavado.app.models.sampleMenuItems
import com.cafelavado.app.theme.DarkBackground
import com.cafelavado.app.theme.TextPrimary
import com.cafelavado.app.theme.TextSecondary

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
        Text(
            text = "Cardápio",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Feito na hora, do jeitinho que você gosta",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(16.dp))

        // --- Category chips ---
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
        ) {
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

        // --- Items list with cross-fade on filter change ---
        AnimatedContent(
            targetState = filteredItems,
            transitionSpec = {
                (slideInHorizontally(tween(260)) { it / 6 } + fadeIn(tween(260))) togetherWith
                    (slideOutHorizontally(tween(260)) { -it / 6 } + fadeOut(tween(200)))
            },
            label = "menu-items",
        ) { items ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
            ) {
                items(items, key = { it.id }) { item ->
                    MenuItemCard(item = item)
                }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}
