# Cite for Kotlin

A Kotlin compiler plugin for accessing information about the file being compiled.

For example, given:
```kotlin
object Greeter {
  fun sayHi() {
    println("Hello from:")
    println("  File: $__FILE__")
    println("  Type: $__TYPE__")
    println("  Member: $__MEMBER__")
    println("  Line: $__LINE__")
  }
}
```
When invoked, this will output:
```
Hello from:
  File: main.kt
  Type: Greeter
  Member: sayHi
  Line: 20
```
The Java bytecode shows values were resolved at compile time:
```
 9: getstatic     #19      // Field java/lang/System.out:Ljava/io/PrintStream;
11: ldc           #27      // String   File: main.kt
14: invokevirtual #25      // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
17: getstatic     #19      // Field java/lang/System.out:Ljava/io/PrintStream;
19: ldc           #29      // String   Type: Greeter
22: invokevirtual #25      // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
25: getstatic     #19      // Field java/lang/System.out:Ljava/io/PrintStream;
27: ldc           #31      // String   Member: sayHi
30: invokevirtual #25      // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
33: getstatic     #19      // Field java/lang/System.out:Ljava/io/PrintStream;
35: ldc           #33      // String   Line: 28
38: invokevirtual #25      // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
```
Every Kotlin target is supported. Here's JS:
```js
Greeter.prototype.h = function () {
  println('Hello from:');
  println('  File: main.kt');
  println('  Type: Greeter');
  println('  Member: sayHi');
  println('  Line: 20');
};
```

## Usage

Coming soon!


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
