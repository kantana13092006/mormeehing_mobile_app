# Feature-First Package Structure Design

## Status

Draft for review.

## Goal

Reorganize the Android Java source and test packages around the Student Work
Hub feature areas without changing runtime behavior, UI, navigation IDs, or
resource names.

## Current Friction

All production classes currently live directly under
`com.example.mormeehing`. A maintainer must scan one flat package to find the
authentication flow, job listing flow, navigation host, and title-only fallback
page. The current structure has low locality: changing one feature requires
searching across unrelated classes.

## Target Structure

```text
app/src/main/java/com/example/mormeehing/
├── core/navigation/
│   └── MainActivity.java
├── feature/auth/
│   ├── LoginFragment.java
│   ├── MockAuthValidator.java
│   └── SplashFragment.java
├── feature/common/
│   └── PlaceholderFragment.java
└── feature/jobs/
    ├── ExampleJobListings.java
    ├── JobListing.java
    ├── JobListingAdapter.java
    └── SearchFragment.java
```

Tests mirror the production seams:

```text
app/src/test/java/com/example/mormeehing/
├── feature/auth/MockAuthValidatorTest.java
└── feature/jobs/ExampleJobListingsTest.java

app/src/androidTest/java/com/example/mormeehing/
├── core/navigation/ExampleInstrumentedTest.java
└── core/navigation/StudentWorkHubNavigationTest.java
```

The package root remains `com.example.mormeehing`, so the generated `R` class
and Android namespace do not change. Classes in subpackages import
`com.example.mormeehing.R` explicitly.

## Integration Updates

- `AndroidManifest.xml` changes the launcher activity reference to
  `com.example.mormeehing.core.navigation.MainActivity`.
- `nav_graph.xml` updates fragment class names for `feature/auth`,
  `feature/common`, and `feature/jobs`.
- Java package declarations and imports are updated consistently.
- `app/build.gradle.kts`, resources, dependency versions, route IDs, and
  resource names remain unchanged.
- The existing `.idea` changes are not part of this refactor.

## Non-Goals

- No new Gradle module.
- No change to navigation behavior or Back-stack behavior.
- No change to the visual design or screen content.
- No new domain/data layer beyond the current feature modules.
- No renaming of resource files unless required by compilation.

## Verification

The refactor is accepted only when all of the following pass:

- `./gradlew test`
- `./gradlew :app:lintDebug`
- `./gradlew :app:assembleDebug`
- `./gradlew :app:connectedDebugAndroidTest`

The instrumentation suite must continue to cover Splash, Login, mock
credentials, bottom navigation, the job listing screen, the FAB, title-only
routes, and Back behavior.

## Decision Rationale

Feature-first grouping gives the authentication and job-listing modules high
locality while keeping the navigation host and title-only fallback in a small
shared seam. A layer-first structure would spread this small app across data,
domain, and presentation directories before there is enough independent
implementation to earn that depth.
