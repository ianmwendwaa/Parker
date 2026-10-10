package org.example.parker

object DateParser {
    private lateinit var dayOfWeek: String
    private lateinit var month: String
    private lateinit var year: String
    private lateinit var time: String
    fun cleanDateTime(dirtyDateTime: String): String{
        // Always expect string value with the syntax: yyyy-MM-ddTHH:mm
        try{
            val dateTimeStr = dirtyDateTime.split("T")
            val (dateStr, timeStr) = dateTimeStr
            val (yearStr, monthStr, dayStr) = dateStr.split('-')
            dayOfWeek = dayStr
            val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec")
            year = yearStr
            month =  months[monthStr.toInt() - 1]
            // AM/PM formatting
            time = timeStr
        }catch (e: Exception){
            println("Error parsing dirty date time provided. Reason: ${e.message}")
        }
        return "$dayOfWeek $month $year at $time ${if (time[0].code < 12) "AM" else "PM"}"
    }
}