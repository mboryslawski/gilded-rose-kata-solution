import com.gildedrose.Item
import com.gildedrose.ItemQualityValidation.MAX_QUALITY
import com.gildedrose.ItemQualityValidation.MIN_QUALITY
import com.gildedrose.changeQualityBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class ItemTest {

    @TestFactory
    fun `quality should not move outside of the quality range`() =
        listOf(
            TestCase("Test minimum range value", Int.MIN_VALUE, MIN_QUALITY),
            TestCase("Test maximum range value", Int.MAX_VALUE, MAX_QUALITY),
        ).map { (testName, value, expected) ->
            dynamicTest(testName) {
                with(randomItem) {
                    changeQualityBy(value)
                    assertEquals(expected, quality)
                }
            }
        }

    private data class TestCase(
        val testName: String,
        val value: Int,
        val expected: Int,
    )
}