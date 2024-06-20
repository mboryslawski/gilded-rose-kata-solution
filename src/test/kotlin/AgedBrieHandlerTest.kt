import com.gildedrose.AgedBrieHandler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class AgedBrieHandlerTest {

    private val agedBrieHandler = AgedBrieHandler

    @Test
    fun `should decrease sellIn by one`() {
        with(randomItem) {
            val sellInBefore = randomItem.sellIn
            agedBrieHandler.handle()
            val sellInAfter = randomItem.sellIn
            assertEquals(sellInBefore - 1, sellInAfter)
        }
    }

    @TestFactory
    fun `should properly decrease quality based on sellIn`() =
        listOf(
            TestCase(1, 10, 11),
            TestCase(0, 10, 12),
            TestCase(-1, 10, 12),
        ).map { (sellIn, givenQuality, expectedQuality) ->
            dynamicTest("Should properly change quality when sellIn: $sellIn") {
                with(item(sellIn, givenQuality)) {
                    agedBrieHandler.handle()
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