package sem.ua.androidintern.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    val userId: Long,
    val name: String,
    val age: Int,
    val dateOfBirth: String,
    var isStudent: Boolean
) : Parcelable



