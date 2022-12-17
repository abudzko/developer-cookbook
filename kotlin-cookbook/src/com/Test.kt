package com

fun main(args: Array<String>) {
    val listOf = listOf("1", null, "22", "вв")
    val filterNotNull = listOf.filterNotNull()
    println(filterNotNull)

}
