# Cite for Kotlin

A Kotlin compiler plugin for embedding information about the file being compiled.

For example, given:
```kotlin
object Greeter {
  fun sayHi() {
    println("Hello: $__FILE__, $__TYPE__, $__MEMBER_, $__LINE__")
  }
}
```
When invoked, this will output:
```
Hello: main.kt, Greeter, sayHi, 16
```
The Java bytecode shows values were resolved at compile time:
```
 9: getstatic     #19      // Field java/lang/System.out:Ljava/io/PrintStream;
11: ldc           #27      // String Hello: main.kt, Greeter, sayHi, 16
14: invokevirtual #25      // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
```
The values are constants, so they can be folded with other constants.

Every Kotlin target is supported. Here's JS:
```js
Greeter.prototype.h = function () {
  println('Hello: main.kt, Greeter, sayHi, 16');
};
```
Native (as LLVM IR, and you might need your ASCII table):
```
@792 = … { …, [34 x i16] [i16 72, i16 101, i16 108, i16 108, i16 111, i16 58, i16 32, i16 109, i16 97, i16 105, i16 110, i16 46, i16 107, i16 116, i16 44, i16 32, i16 71, i16 114, i16 101, i16 101, i16 116, i16 101, i16 114, i16 44, i16 32, i16 115, i16 97, i16 121, i16 72, i16 105, i16 44, i16 32, i16 49, i16 54] }
 ⋮
call void @Kotlin_io_Console_println(… ({ %struct.ArrayHeader, [34 x i16] }* @792 …)), !dbg !12017
```


## API

Four properties are provided:

| Property     | Type     | Description                                                      | Example   |
|--------------|----------|------------------------------------------------------------------|-----------|
| `__FILE__`   | `String` | Filename of the source file.                                     | "main.kt" |
| `__TYPE__`   | `String` | Name of the nearest enclosing class, object, interface, or enum. | "Greeter" |
| `__MEMBER__` | `String` | Name of the nearest enclosing function or property body.         | "sayHi"   |
| `__LINE__`   | `Int`    | One-based line number of this property access.                   | 16        |

Use of a property in a location without an associated enclosing type is an error.
For example, using `__TYPE__` in a top-level function will fail to compile.


## Usage

Add the buildscript dependency and apply the plugin to each module in which
you want to access the properties.

```groovy
buildscript {
  repository {
    mavenCental()
  }
  dependencies {
    classpath 'com.jakewharton.cite:cite-gradle-plugin:0.2.0'
  }
}

apply plugin: 'org.jetbrains.kotlin.multiplatform' // Or .jvm
apply plugin: 'com.jakewharton.cite'
```

The runtime dependency will be added as an `implementation` dependency automatically.
Never add the runtime dependency yourself, as use without the plugin will not work.

<details>
<summary>Snapshots of the development version are available in Sonatype's snapshots repository.</summary>
<p>

```groovy
buildscript {
  repositories {
    mavenCentral()
    maven {
      url 'https://oss.sonatype.org/content/repositories/snapshots/'
    }
  }
  dependencies {
    classpath 'com.jakewharton.cite:cite-gradle-plugin:0.3.0-SNAPSHOT'
  }
}

apply plugin: 'org.jetbrains.kotlin.multiplatform' // Or .jvm
apply plugin: 'com.jakewharton.cite'
```

</p>
</details>

### Compatibility

Since Kotlin compiler plugins are an unstable API, certain versions of Cite only work with
certain versions of Kotlin.

| Kotlin         | Cite          |
|----------------|---------------|
| 1.8.0 - 1.9.10 | 0.1.0 - 0.2.0 |

Kotlin versions newer than those listed may be supported but have not been tested.


# License

    Copyright 2023 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
