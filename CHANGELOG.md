# Change Log

## [Unreleased]
[Unreleased]: https://github.com/JakeWharton/cite/compare/0.4.0...HEAD

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
