package io.github.dllewellyn.safetorun.offdevice

import android.content.SharedPreferences

class FakeSharedPrefs : SharedPreferences {

    private val map = mutableMapOf<String, String>()

    override fun getAll(): MutableMap<String, *> {
        TODO("Not yet implemented")
    }

    override fun getString(p0: String?, p1: String?): String? {
        return map[p0]
    }

    override fun getStringSet(p0: String?, p1: MutableSet<String>?): MutableSet<String>? {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: String?, p1: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLong(p0: String?, p1: Long): Long {
        TODO("Not yet implemented")
    }

    override fun getFloat(p0: String?, p1: Float): Float {
        TODO("Not yet implemented")
    }

    override fun getBoolean(p0: String?, p1: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(p0: String?): Boolean {
        return map.contains(p0)
    }

    override fun edit(): SharedPreferences.Editor {
        return object : SharedPreferences.Editor {
            override fun putString(p0: String?, p1: String?): SharedPreferences.Editor {
                map.put(p0!!, p1!!)
                return this
            }

            override fun putStringSet(p0: String?, p1: MutableSet<String>?): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putInt(p0: String?, p1: Int): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putLong(p0: String?, p1: Long): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putFloat(p0: String?, p1: Float): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun putBoolean(p0: String?, p1: Boolean): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun remove(p0: String?): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun clear(): SharedPreferences.Editor {
                TODO("Not yet implemented")
            }

            override fun commit(): Boolean {
                TODO("Not yet implemented")
            }

            override fun apply() {
                // NOOP
            }
        }
    }

    override fun registerOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }

    override fun unregisterOnSharedPreferenceChangeListener(p0: SharedPreferences.OnSharedPreferenceChangeListener?) {
        TODO("Not yet implemented")
    }
}
