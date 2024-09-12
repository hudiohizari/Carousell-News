package com.hizari.domain.model

import android.content.Context
import com.hizari.common.extention.orZero
import com.hizari.data.R
import com.hizari.data.network.model.dto.NewsDTO
import java.util.concurrent.TimeUnit

data class News(
    val id: String,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val timeAgo: String
) {

    companion object {
        fun from(context: Context, data: NewsDTO): News {
            return News(
                id = data.id.orEmpty(),
                title = data.title.orEmpty(),
                description = data.description.orEmpty(),
                bannerUrl = data.bannerUrl.orEmpty(),
                timeAgo = getTimeAgo(context, data.timeCreated.orZero())
            )
        }

        private fun getTimeAgo(context: Context, timeCreated: Long): String {
            val now = System.currentTimeMillis() / 1000 // Current time in seconds
            val diffInSeconds = now - timeCreated

            val secondsInMinute = TimeUnit.MINUTES.toSeconds(1)
            val secondsInHour = TimeUnit.HOURS.toSeconds(1)
            val secondsInDay = TimeUnit.DAYS.toSeconds(1)
            val secondsInWeek = TimeUnit.DAYS.toSeconds(7)
            val secondsInMonth = 30 * secondsInDay
            val secondsInYear = 365 * secondsInDay

            return when {
                diffInSeconds >= secondsInYear -> {
                    val years = diffInSeconds / secondsInYear
                    context.resources.getQuantityString(R.plurals.years_ago, years.toInt(), years)
                }
                diffInSeconds >= secondsInMonth -> {
                    val months = diffInSeconds / secondsInMonth
                    context.resources.getQuantityString(R.plurals.months_ago, months.toInt(), months)
                }
                diffInSeconds >= secondsInWeek -> {
                    val weeks = diffInSeconds / secondsInWeek
                    context.resources.getQuantityString(R.plurals.weeks_ago, weeks.toInt(), weeks)
                }
                diffInSeconds >= secondsInDay -> {
                    val days = diffInSeconds / secondsInDay
                    context.resources.getQuantityString(R.plurals.days_ago, days.toInt(), days)
                }
                diffInSeconds >= secondsInHour -> {
                    val hours = diffInSeconds / secondsInHour
                    context.resources.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours)
                }
                diffInSeconds >= secondsInMinute -> {
                    val minutes = diffInSeconds / secondsInMinute
                    context.resources.getQuantityString(R.plurals.minutes_ago, minutes.toInt(), minutes)
                }
                else -> context.getString(R.string.just_now)
            }
        }
    }

}