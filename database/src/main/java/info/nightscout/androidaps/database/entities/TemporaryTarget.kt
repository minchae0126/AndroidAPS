package info.nightscout.androidaps.database.entities

import androidx.annotation.NonNull
import androidx.room.*
import info.nightscout.androidaps.database.TABLE_TEMPORARY_TARGETS
import info.nightscout.androidaps.database.embedments.InterfaceIDs
import info.nightscout.androidaps.database.interfaces.DBEntry
import info.nightscout.androidaps.database.interfaces.DBEntryWithTimeAndDuration

@Entity(tableName = TABLE_TEMPORARY_TARGETS,
        foreignKeys = [ForeignKey(
                entity = TemporaryTarget::class,
                parentColumns = ["id"],
                childColumns = ["referenceID"])],
        indices = [Index("referenceID"), Index("timestamp")])
data class TemporaryTarget(
        @PrimaryKey(autoGenerate = true)
        override var id: Long = 0,
        override var version: Int = 0,
        override var lastModified: Long = -1,
        override var valid: Boolean = true,
        override var referenceID: Long? = null,
        @Embedded
        override var interfaceIDs2: InterfaceIDs? = InterfaceIDs(),
        override var timestamp: Long,
        override var utcOffset: Long,
        var reason: Reason,
        var target: Double,
        override var duration: Long
) : DBEntry, DBEntryWithTimeAndDuration {
    enum class Reason {
        CUSTOM,
        HYPOGLYCEMIA,
        ACTIVITY,
        EATING_SOON
    }
}