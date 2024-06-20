import com.gildedrose.BackstagePassesHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class BackstagePassesHandlerTest {

    private val backstagePassesHandler = BackstagePassesHandler

    @Test
    fun `should decrease sellIn by one`() {
        with(randomItem) {
            val sellInBefore = randomItem.sellIn
            backstagePassesHandler.handle()
            val sellInAfter = randomItem.sellIn
            assertEquals(sellInBefore - 1, sellInAfter)
        }
    }

    @TestFactory
    fun `should properly decrease quality based on sellIn`() =
        listOf(
            TestCase(-1, 10, 0),
            TestCase(0, 10, 0),
            TestCase(4, 10, 13),
            TestCase(5, 10, 13),
            TestCase(6, 10, 12),
            TestCase(9, 10, 12),
            TestCase(10, 10, 12),
            TestCase(11, 10, 11),
            TestCase(11, 10, 11),
        ).map { (sellIn, givenQuality, expectedQuality) ->
            dynamicTest("Should properly change quality when sellIn: $sellIn") {
                with(item(sellIn, givenQuality)) {
                    backstagePassesHandler.handle()
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