package com.ad8.presentation.util

import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*

open class DayAxisValueFormatter(
    private val chart: BarLineChartBase<*>,
    private val dates: List<Calendar>? = null
) : ValueFormatter() {
    private val mMonths = arrayOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )

    override fun getFormattedValue(value: Float): String {
        //val days = value.toInt()
        val index = value.toInt()
        if (dates.isNullOrEmpty())
            return ""

        try {
            val year = dates[index].get(Calendar.YEAR)
            val month = dates[index].get(Calendar.MONTH)
            val monthName = mMonths[month % mMonths.size]
            val yearName = year.toString()
            return if (chart.visibleXRange > 30 * 6) {
                "$monthName $yearName"
            } else {
                val dayOfMonth = dates[index].get(Calendar.DAY_OF_MONTH)
                var appendix = "th"
                when (dayOfMonth) {
                    1 -> appendix = ""//st
                    2 -> appendix = "nd"
                    3 -> appendix = "rd"
                    21 -> appendix = "st"
                    22 -> appendix = "nd"
                    23 -> appendix = "rd"
                    31 -> appendix = "st"
                }
                if (dayOfMonth == 0) "" else "${if(dayOfMonth>1)dayOfMonth else ""}$appendix $monthName"
            }
        }
        catch (e:IndexOutOfBoundsException){
            return ""
        }

    }


}