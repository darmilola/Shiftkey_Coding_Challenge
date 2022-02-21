package com.shiftkey.codingchallenge.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class DateUtil {
    private var dateFormat: DateFormat? = null

    init {
        dateFormat = SimpleDateFormat("yyyy-MM-dd")
    }

    fun getCurrentStartDate(): String {
        val date = Date()
        return dateFormat!!.format(date)
    }

    fun getNextStartDate(dateString: String): String {
        val c = Calendar.getInstance()
        try {
            c.time = dateFormat!!.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        c.add(Calendar.DAY_OF_MONTH, 6)
        return dateFormat!!.format(c.time)
    }

    fun getEndDate(startDate: String): String {
        val c = Calendar.getInstance()
        try {
            c.time = dateFormat!!.parse(startDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        c.add(Calendar.DAY_OF_MONTH, 6)
        return dateFormat!!.format(c.time)
    }

    fun extractDateFromString(dateString: String): String {
        val regex = "(\\d{4}-\\d{2}-\\d{2})"
        val m: Matcher = Pattern.compile(regex).matcher(dateString)
        if (m.find()) {
            val date = m.group(1)
            return date.toString()
        }
        return ""
    }

    fun extractTimeFromString(dateString: String): String {
        val regex = "(\\d{2}:\\d{2}:\\d{2})"
        val m: Matcher = Pattern.compile(regex).matcher(dateString)
        if (m.find()) {
            return m.group(1)
        }
        return ""
    }

    fun formatDateTimeIn12HourFormat(dateString: String): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm aa")
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

    fun formatDateInLiteralFormat(dateString: String): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat: DateFormat = SimpleDateFormat("dd MMM, yyyy")
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date)
    }

}