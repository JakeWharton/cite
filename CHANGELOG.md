# Change Log

## [Unreleased]
[Unreleased]: https://github.com/JakeWharton/cite/compare/0.9.0...HEAD

Nothing yet!


## [0.9.0] - 2026-06-03
[0.9.0]: https://github.com/JakeWharton/cite/releases/tag/0.9.0

New:
- Support for Kotlin 2.4.0.
- Add `__MODULE__` which returns the name or the Kotlin module (as specified by the `--module-name` argument to the compiler).
- Add `__FQ_NAME__` which returns a fully-qualified type name or the file facade name.


## [0.8.0] - 2025-12-18
[0.8.0]: https://github.com/JakeWharton/cite/releases/tag/0.8.0

New:
- Support for Kotlin 2.3.0.

Changed:
- The minimum-supported Gradle version is now 9.0.


## [0.7.0] - 2025-09-23
[0.7.0]: https://github.com/JakeWharton/cite/releases/tag/0.7.0

Fix:
- Add support for Android Gradle plugin 9.0's new built-in Kotlin support.

Changed:
- In-development snapshots are now published to the Central Portal Snapshots repository at https://central.sonatype.com/repository/maven-snapshots/.


## [0.6.1] - 2025-01-06
[0.6.1]: https://github.com/JakeWharton/cite/releases/tag/0.6.1

Fix:
- Change Gradle plugin to be more compatible with forthcoming project isolation feature.


## [0.6.0] - 2024-11-26
[0.6.0]: https://github.com/JakeWharton/cite/releases/tag/0.6.0

New:
- Add the runtime as `compileOnly` additionally to test compilations for JVM and Android projects.


## [0.5.0] - 2024-11-20
[0.5.0]: https://github.com/JakeWharton/cite/releases/tag/0.5.0

New:
- Change runtime dependency to `compileOnly` on JVM and Android projects.
- Renamed `cite-runtime` to `cite-api` since there's no actual runtime behavior.


## [0.4.0] - 2024-11-11
[0.4.0]: https://github.com/JakeWharton/cite/releases/tag/0.4.0

New:

- Support Kotlin 2.0.21 at the K2 compiler. Older Kotlin 2.0.x versions may also work, but
  are untested.


## [0.3.0] - 2024-11-07
[0.3.0]: https://github.com/JakeWharton/cite/releases/tag/0.3.0

New:

- Support usage with the `org.jetbrains.kotlin.android` plugin.

Changed:

- Legacy `wasm` target removed. `wasmJs` and `wasmWasi` targets added.


## [0.2.0] - 2023-09-14
[0.2.0]: https://github.com/JakeWharton/cite/releases/tag/0.2.0

New:
- Add WASM target


## [0.1.0] - 2023-02-08
[0.1.0]: https://github.com/JakeWharton/cite/releases/tag/0.1.0

Initial release.
