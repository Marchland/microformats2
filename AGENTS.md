# Project agent memory

Library for parsing HTML into microformats2 JSON (the canonical
`{items, rels, rel-urls}` shape), plus the `Mf2Object`/`Mf2Value` model and Jackson 3
serializers. Pure JVM library; depends only on jsoup and Jackson 3 (databind).

- `dev.jacobandersen.microformats2` - model + parser. `Mf2Parser.parse(html, baseUrl)` is a pure
  function; implementations are stateless and safe for concurrent reuse.
- `dev.jacobandersen.microformats2.json` - `ObjectNode.toMf2Object()` and the value (de)serializers.
- No post-type / Micropub / IndieAuth knowledge lives here; that belongs to callers.

## Build and test

- `./gradlew test` runs the suite; `./gradlew ktlintCheck` lints (`ktlintFormat` fixes).
- Jackson 3 (`tools.jackson.*`) is used, not Jackson 2.
- Version is `version.txt`; published to GitHub Packages Maven as `dev.jacobandersen:microformats2`.
  Consumers need `mavenLocal()` during development or the GitHub Packages repo + a PAT.
