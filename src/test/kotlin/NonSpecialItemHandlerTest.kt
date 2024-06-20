import com.gildedrose.NonSpecialItemHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class NonSpecialItemHandlerTest {

    private val nonSpecialHandler = NonSpecialItemHandler

    @Test
    fun `should decrease sellIn by one`() {
        with(randomItem) {
            val sellInBefore = randomItem.sellIn
            nonSpecialHandler.handle()
            val sellInAfter = randomItem.sellIn
            assertEquals(sellInBefore - 1, sellInAfter)
        }
    }

    @TestFactory
    fun `should properly decrease quality based on sellIn`() =
        listOf(
            TestCase(1, 10, 9),
            TestCase(0, 10, 8),
            TestCase(-1, 10, 8),
        ).map { (sellIn, givenQuality, expectedQuality) ->
            dynamicTest("Should properly change quality when sellIn: $sellIn") {
                with(item(sellIn, givenQuality)) {
                    nonSpecialHandler.handle()
                    assertEquals(expectedQuality, quality)
                }
            }
        }

    private data class TestCase(
        val sellIn: Int,
        val givenQuality: Int,
        val expectedQuality: Int,
    )
}