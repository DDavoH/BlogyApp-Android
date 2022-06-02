package android.davoh.blogyapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
   
    fun dateTimeServerToLocal(value: String?): String{
        val dateStr = value
        val curFormater = SimpleDateFormat("yyyy-MM-dd")
        val dateObj: Date = curFormater.parse(dateStr)
    
       return curFormater.format(dateObj)
    }
    
}