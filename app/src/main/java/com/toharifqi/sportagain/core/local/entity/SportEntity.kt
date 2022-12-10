package com.toharifqi.sportagain.core.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toharifqi.sportagain.core.domain.SportDomainData

@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "sportId")
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "format")
    var format: String,

    @ColumnInfo(name = "thumb")
    var thumbnail: String,

    @ColumnInfo(name = "icon")
    var icon: String,

    @ColumnInfo(name = "description")
    var description: String
) {
    constructor(domainData: SportDomainData) : this(
        id = domainData.id,
        name = domainData.name,
        format = domainData.format,
        thumbnail = domainData.thumbnail,
        icon = domainData.icon,
        description = domainData.description
    )
}
