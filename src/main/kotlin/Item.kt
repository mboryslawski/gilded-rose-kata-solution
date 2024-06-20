package com.gildedrose

class Item(var name: String, var sellIn: Int, var quality: Int) {
    override fun toString(): String {
        return this.name + ", " + this.sellIn + ", " + this.quality
    }
}

context(Item)
fun decreaseSellIn() {
    sellIn--
}

context(Item)
fun changeQualityBy(value: Int) {
    when {
        (quality + value) > ItemQualityValidation.MAX_QUALITY -> quality = ItemQualityValidation.MAX_QUALITY
        (quality + value) < ItemQualityValidation.MIN_QUALITY -> quality = ItemQualityValidation.MIN_QUALITY
        else -> quality += value
    }
}

context(Item)
fun destroyItem() {
    quality = ItemQualityValidation.MIN_QUALITY
}

