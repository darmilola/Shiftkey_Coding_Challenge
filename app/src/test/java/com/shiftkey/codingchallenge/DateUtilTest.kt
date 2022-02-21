package com.shiftkey.codingchallenge

import com.shiftkey.codingchallenge.utils.DateUtil
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateUtilTest {

    @Test
    fun `Assert Current Date is Now`() {
        Assert.assertEquals(DateUtil().getCurrentStartDate(), SimpleDateFormat("yyyy-MM-dd").format(Date()))
    }

    @Test
    fun `Assert Next Start Date`() {
        Assert.assertEquals(DateUtil().getNextStartDate("2022-02-20"), "2022-02-26")
    }

    @Test
    fun `Assert End Date`() {
        Assert.assertEquals(DateUtil().getEndDate("2022-03-20"), "2022-03-26")
    }

    @Test
    fun `Assert Date From String`() {
        Assert.assertEquals(DateUtil().extractDateFromString("2022-02-20 06:00:00"), "2022-02-20")
    }

    @Test
    fun `Assert Time From String`() {
        Assert.assertEquals(DateUtil().extractTimeFromString("2022-02-20 06:00:00"), "06:00:00")
    }

    @Test
    fun `Assert Date Time in 12Hour Format`() {
        Assert.assertEquals(DateUtil().formatDateTimeIn12HourFormat("2022-02-20 06:00:00"), "20 Feb, 2022 06:00 AM")
    }

    @Test
    fun `Assert Date in 12Hour Format`() {
        Assert.assertEquals(DateUtil().formatDateInLiteralFormat("2022-02-20"), "20 Feb, 2022")
    }
}