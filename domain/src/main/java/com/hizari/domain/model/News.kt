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
            val now = System.currentTimeMillis() / 1000
            val diffInSeconds = now - timeCreated

            val minutes = TimeUnit.SECONDS.toMinutes(diffInSeconds)
            val hours = TimeUnit.SECONDS.toHours(diffInSeconds)
            val days = TimeUnit.SECONDS.toDays(diffInSeconds)
            val weeks = days / 7
            val months = days / 30
            val years = days / 365

            return when {
                diffInSeconds < 60 -> context.getString(R.string.just_now)
                minutes < 60 -> {
                    context.resources.getQuantityString(R.plurals.minutes_ago, minutes.toInt(), minutes)
                }
                hours < 60 -> {
                    context.resources.getQuantityString(R.plurals.hours_ago, hours.toInt(), hours)
                }
                days < 7 -> {
                    context.resources.getQuantityString(R.plurals.days_ago, days.toInt(), days)
                }
                weeks < 4 -> {
                    context.resources.getQuantityString(R.plurals.weeks_ago, weeks.toInt(), weeks)
                }
                months < 12 -> {
                    context.resources.getQuantityString(R.plurals.months_ago, months.toInt(), months)
                }
                else -> {
                    context.resources.getQuantityString(R.plurals.years_ago, years.toInt(), years)
                }
            }
        }
    }

}