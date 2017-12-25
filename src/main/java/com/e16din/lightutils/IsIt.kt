package com.e16din.lightutils

import android.telephony.PhoneNumberUtils
import android.util.Patterns
import com.e16din.lightutils.model.Countries

/**
 * Created by e16din on 02.09.15.
 */
object IsIt {

    @JvmOverloads
    fun phone(phoneStr: String, countryId: Int = Countries.ID_NONE) =
            (phoneStr.length in 7..12 && PhoneNumberUtils.isGlobalPhoneNumber(phoneStr))

    @JvmOverloads
    fun email(s: CharSequence) = Patterns.EMAIL_ADDRESS.matcher(s).matches()

    @JvmOverloads
    fun number(s: String) = (s.toDoubleOrNull() != null)

    @JvmOverloads
    fun nil(obj: Any?) = (obj == null)
}
