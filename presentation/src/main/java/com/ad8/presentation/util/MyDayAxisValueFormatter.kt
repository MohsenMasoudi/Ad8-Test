package com.utechia.presentation.util

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.formatter.ValueFormatter

open class MyDayAxisValueFormatter(private val chart: BarLineChartBase<*>) : ValueFormatter() {
    private val mMonths = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    override fun getFormattedValue(value: Float): String {
        val date=(value*1000000).toInt().toString()
        val year = date.substring(0,3).toInt()
        val month =date.substring(4,5).toInt()
        val day =date.substring(6,7).toInt()
        val monthName = mMonths[month % mMonths.size]
        val yearName = year.toString()
        return if (chart.visibleXRange > 30 * 6) {
            "$monthName $yearName"
        } else {
            var appendix = "th"
            when (day) {
                1 -> appendix = "st"
                2 -> appendix = "nd"
                3 -> appendix = "rd"
                21 -> appendix = "st"
                22 -> appendix = "nd"
                23 -> appendix = "rd"
                31 -> appendix = "st"
            }
            if (day == 0) "" else "$day$appendix $monthName"
        }
    }

    private fun getDaysForMonth(month: Int, year: Int): Int {

        // month is 0-based
        if (month == 1) {
            var is29Feb = false
            if (year < 1582) is29Feb =
                (if (year < 1) year + 1 else year) % 4 == 0 else if (year > 1582) is29Feb =
                year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
            return if (is29Feb) 29 else 28
        }
        return if (month == 3 || month == 5 || month == 8 || month == 10) 30 else 31
    }

    private fun determineMonth(dayOfYear: Int): Int {
        var month = -1
        var days = 0
        while (days < dayOfYear) {
            month = month + 1
            if (month >= 12) month = 0
            val year = determineYear(days)
            days += getDaysForMonth(month, year)
        }
        return Math.max(month, 0)
    }

    private fun determineDayOfMonth(days: Int, month: Int): Int {
        var count = 0
        var daysForMonths = 0
        while (count < month) {
            val year = determineYear(daysForMonths)
            daysForMonths += getDaysForMonth(count % 12, year)
            count++
        }
        return days - daysForMonths
    }

    private fun determineYear(days: Int): Int {
        return if (days <= 366) 2020 else if (days <= 730) 2021 else if (days <= 1094) 2022 else if (days <= 1458) 2023 else 2024
    }
}