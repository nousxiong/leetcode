package me.xiong

import org.junit.jupiter.api.Test
import java.util.concurrent.ThreadLocalRandom
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

/**
 * Created by xiongxl on 2022/3/18
 */
class ArrayAndStringTest {
    /**
     * https://leetcode-cn.com/leetbook/read/array-and-string/yf47s/
     */
    @Test
    fun c1t1_pivotIndex() {
        fun pivotIndex(nums: IntArray): Int {
            var result = -1
            val rightSums = IntArray(nums.size) { 0 }
            var curRightSum = 0
            var prevRight = 0
            (nums.size - 1 downTo  0).forEach { i ->
                curRightSum += prevRight
                rightSums[i] = curRightSum
                prevRight = nums[i]
            }
            var curLeftSum = 0
            var prevLeft = 0
            for (i in nums.indices) {
                curLeftSum += prevLeft
                if (curLeftSum == rightSums[i]) {
                    result = i
                    break
                }
                prevLeft = nums[i]
            }
            return result
        }
        assertEquals(3, pivotIndex(arrayOf(1, 7, 3, 6, 5, 6).toIntArray()))
        assertEquals(-1, pivotIndex(arrayOf(1, 2, 3).toIntArray()))
        assertEquals(0, pivotIndex(arrayOf(2, 1, -1).toIntArray()))
        val nums = ThreadLocalRandom.current().ints(10000, -1000, 1001).toArray()
        val ms = measureTimeMillis {
            pivotIndex(nums)
        }
        println("measure 10000 pivotIndex ${ms}ms")
    }
}