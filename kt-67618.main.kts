#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:Repository("file://~/.m2/repository/")
@file:DependsOn("moe.nikky:kt_67618:0.1-SNAPSHOT")

println("hello world")

someLibraryMethod()
doHttpCall()
