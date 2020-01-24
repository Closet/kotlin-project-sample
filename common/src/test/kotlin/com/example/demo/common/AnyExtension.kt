package com.example.demo.common

fun Any.changeProperties(to: MutableList<Pair<String, Any>>): Any {
    to.forEach {
        this::class.java.getDeclaredField(it.first).apply {
            isAccessible = true
            set(this, it.second)
        }
    }
    return this
}

fun Any.changeProperty(to: Pair<String, Any>): Any {
    this::class.java.getDeclaredField(to.first)
            .apply {
                isAccessible = true
                set(this, to.second)
            }
    return this
}
