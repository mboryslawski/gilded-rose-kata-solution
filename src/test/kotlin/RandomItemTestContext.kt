import com.gildedrose.Item

const val NOT_IMPORTANT_VALUR_FOR_TEST_STRING = ""
const val NOT_IMPORTANT_VALUR_FOR_TEST_INT = 0

val randomItem =
    Item(
        NOT_IMPORTANT_VALUR_FOR_TEST_STRING,
        NOT_IMPORTANT_VALUR_FOR_TEST_INT,
        NOT_IMPORTANT_VALUR_FOR_TEST_INT
    )


fun item(sellIn: Int, quality: Int) = Item(NOT_IMPORTANT_VALUR_FOR_TEST_STRING, sellIn, quality)