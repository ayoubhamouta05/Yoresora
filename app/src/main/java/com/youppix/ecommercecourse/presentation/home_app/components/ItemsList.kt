package com.youppix.ecommercecourse.presentation.home_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.youppix.ecommercecourse.domain.model.items.Item

@Composable
fun ItemsList(items: List<Item>) {
   Column {
       repeat(6){
           Row{
               ItemsListItem(item = items[0])
               ItemsListItem(item = items[0])
           }
       }
   }
}
