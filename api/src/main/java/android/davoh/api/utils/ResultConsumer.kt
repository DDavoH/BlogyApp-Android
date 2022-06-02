package android.davoh.api.utils

interface ResultConsumer<T> {
    fun consume(result: ResultSimple<T>)
}