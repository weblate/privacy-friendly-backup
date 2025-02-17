package org.secuso.privacyfriendlybackup.ui.common

import androidx.annotation.ColorRes
import org.secuso.privacyfriendlybackup.R
import java.lang.Exception

/**
 * Marks which modes are active. Multiple modes can be active at the same time.
 */
enum class Mode(var value: Int, @ColorRes var color: Int) {
    NORMAL(0, R.color.colorPrimary),
    SEARCH(1, R.color.colorAccent),
    DELETE(2, R.color.middlegrey),
    SEARCH_AND_DELETE(3, R.color.middleblue),
    EXPORT(4, R.color.green),
    SEARCH_AND_EXPORT(5, R.color.lightgreen);

    fun isActiveIn(currentMode: Mode): Boolean {
        return currentMode.value and value == value
    }

    fun activate(_mode: Mode): Mode {
        return values().find { it.value == (this.value or _mode.value) } ?: this
    }

    fun deactivate(_mode: Mode): Mode {
        return values().find { it.value == (this.value and _mode.value.inv()) } ?: this
    }

    companion object {
        operator fun get(i: Int): Mode {
            for (mode in Mode.values()) {
                if (mode.value == i) return mode
            }
            return NORMAL
        }
    }
}