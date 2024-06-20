package com.gildedrose

class ItemHandlersFactory {

    private val itemHandlersMap = mapOf(
        "Aged Brie" to AgedBrieHandler,
        "Backstage passes to a TAFKAL80ETC concert" to BackstagePassesHandler,
        "Sulfuras, Hand of Ragnaros" to SulfurasHandler,
        "Conjured" to ConjuredItemHandler,
    )

    context(Item)
    fun getItemHandler() =
        itemHandlersMap.getOrDefault(name, NonSpecialItemHandler)

}