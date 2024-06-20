import com.gildedrose.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

private const val NOT_IMPORTANT_VALUR_FOR_TEST = 0

class ItemHandlersFactoryTest {

    private val itemHandlersFactory = ItemHandlersFactory()

    @TestFactory
    fun `should return appropriate item handler`() = listOf(
        TestCase("Aged Brie", AgedBrieHandler),
        TestCase("Backstage passes to a TAFKAL80ETC concert", BackstagePassesHandler),
        TestCase("Sulfuras, Hand of Ragnaros", SulfurasHandler),
        TestCase("Conjured", ConjuredItemHandler),
        TestCase("Some random item", NonSpecialItemHandler)
    ).map { (itemName, expectedHandler) ->
        dynamicTest("Should return $expectedHandler for $itemName") {
            val handler = with(item(itemName)) {
                itemHandlersFactory.getItemHandler()
            }
            assertEquals(expectedHandler, handler)
        }
    }

    private fun item(itemName: String) = Item(itemName, NOT_IMPORTANT_VALUR_FOR_TEST, NOT_IMPORTANT_VALUR_FOR_TEST)

    private data class TestCase(
        val itemName: String,
        val expectedHandler: ItemHandler
    )
}