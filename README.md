# KT-67618

```shell
./gradlew publishToMavenLocal

./kt-67618.main.kts
```

expected output:

```
hello world
some library method was called
calling getKtorIo
java.lang.NoClassDefFoundError: io/ktor/client/engine/cio/CIO
	at HttpKt.<clinit>(Http.kt:14)
	at MainKt.doHttpCall(Main.kt:3)
	at Kt_67618_main.<init>(kt-67618.main.kts:10)
Caused by: java.lang.ClassNotFoundException: io.ktor.client.engine.cio.CIO
	at java.base/java.net.URLClassLoader.findClass(URLClassLoader.java:476)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:594)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:527)
```
