package com.gildedrose

import com.gildedrose.SpecialItemIdentifier.*

class ItemHandlersFactory {

    private val itemHandlers = SpecialItemIdentifier.entries.map {
        when (it) {
            AGED_BRIE -> ItemHandlerPair(it.itemName, AgedBrieHandler)
            BACKSTAGE_PASSES -> ItemHandlerPair(it.itemName, BackstagePassesHandler)
            SULFURAS -> ItemHandlerPair(it.itemName, SulfurasHandler)
            CONJURED -> ItemHandlerPair(it.itemName, ConjuredItemHandler)
        }
    }

    data class ItemHandlerPair(val itemName: String, val itemHandler: ItemHandler)


    context(Item)
    fun getItemHandler() =
        itemHandlers.firstOrNull { it.itemName == name }?.itemHandler ?: NonSpecialItemHandler
}