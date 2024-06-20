package com.gildedrose

sealed interface ItemHandler {
    context(Item)
    fun handle()
}

data object NonSpecialItemHandler : ItemHandler {
    /**
     * Once the sell by date has passed, Quality degrades twice as fast
     */
    context(Item)
    override fun handle() {
        decreaseSellIn()
        when {
            sellIn < 0 -> changeQualityBy(-2)
            else -> changeQualityBy(-1)
        }
    }
}

data object BackstagePassesHandler : ItemHandler {
    /**
     * Increases in Quality as its SellIn value approaches;
     *  - quality increases by 2 when there are 10 days or less
     *  - quality increase by 3 when there are 5 days or less
     *  - quality drops to 0 after
     */
    context(Item)
    override fun handle() {
        decreaseSellIn()
        when {
            sellIn < 0 -> destroyItem()
            sellIn < 5 -> changeQualityBy(3)
            sellIn < 10 -> changeQualityBy(2)
            else -> changeQualityBy(1)
        }
    }
}

data object AgedBrieHandler : ItemHandler {
    /**
     * Increases in Quality the older it gets,
     * and do it twice as fast if sell date has passed
     */
    context(Item)
    override fun handle() {
        decreaseSellIn()
        when {
            sellIn < 0 -> changeQualityBy(2)
            else -> changeQualityBy(1)
        }

    }
}

data object SulfurasHandler : ItemHandler {
    context(Item)
    override fun handle() {
        // Immutable item's handler. It is empty by purpose.
    }
}

data object ConjuredItemHandler : ItemHandler {
    /**
     * Items degrade in Quality twice as fast as normal items.
     * Maybe we should add the dependency here to NonSpecialItemHandler?
     */
    context(Item)
    override fun handle() {
        decreaseSellIn()
        when {
            sellIn < 0 -> changeQualityBy(-4)
            else -> changeQualityBy(-2)
        }
    }
}