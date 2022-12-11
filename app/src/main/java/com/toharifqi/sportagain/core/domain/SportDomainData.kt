package com.toharifqi.sportagain.core.domain

import android.os.Parcelable
import com.toharifqi.sportagain.core.remote.response.SportsItemResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class SportDomainData(
    var id: String,
    var name: String,
    var format: String,
    var thumbnail: String,
    var icon: String,
    var description: String
) : Parcelable {
    constructor(response: SportsItemResponse) : this(
        id = response.idSport,
        name = response.strSport,
        format = response.strFormat,
        thumbnail = response.strSportThumb,
        icon = response.strSportIconGreen,
        description = response.strSportDescription
    )
}
