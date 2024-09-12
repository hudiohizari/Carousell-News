package com.hizari.data.network.model.dto

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.hizari.common.extention.orZero
import com.hizari.data.R
import com.hizari.domain.model.News
import java.util.concurrent.TimeUnit

data class NewsDTO(
    val id: String?,
    val title: String?,
    val description: String?,
    @SerializedName("banner_url")
    val bannerUrl: String?,
    @SerializedName("time_created")
    val timeCreated: Long?,
    val rank: Int?
) {

    fun toDomain(context: Context): News {
        return News(
            id = id.orEmpty(),
            title = title.orEmpty(),
            description = description.orEmpty(),
            bannerUrl = bannerUrl.orEmpty(),
            timeAgo = getTimeAgo(context, timeCreated.orZero())
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