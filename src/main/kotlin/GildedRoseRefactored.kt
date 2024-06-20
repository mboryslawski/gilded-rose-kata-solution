package com.gildedrose


class GildedRoseRefactored(private val items: List<Item>) {

    private val itemHandlersFactory = ItemHandlersFactory()

    fun updateQuality() {
        items.forEach { item ->
            with(item) {
                val itemHandler = itemHandlersFactory.getItemHandler()
                itemHandler.handle()
            }
        }
    }
}


