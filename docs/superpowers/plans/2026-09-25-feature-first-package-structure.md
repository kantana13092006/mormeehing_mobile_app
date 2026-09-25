# Feature-First Package Structure Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Reorganize the existing Android Java source and test packages around the Student Work Hub feature areas without changing runtime behavior, UI, navigation IDs, resource names, or dependencies.

**Architecture:** Keep the existing `com.example.mormeehing` namespace and split only the Java packages into `core.navigation`, `feature.auth`, `feature.jobs`, and `feature.common`. The navigation host remains the composition point; feature classes remain directly dependent on Android UI and the existing resources. No new Gradle module or data/domain layer is introduced.

**Tech Stack:** Native Android, Java, XML layouts/navigation, AndroidX Navigation, Material Components, JUnit, Espresso, Gradle.

**Spec:** `docs/superpowers/specs/2026-09-25-feature-first-package-structure-design.md`

## Global Constraints

- Preserve the `com.example.mormeehing` namespace so generated `R` references and the application package remain stable.
- Update package declarations, imports, manifest activity references, navigation fragment class names, and test package paths together; no stale flat-package references may remain.
- Keep all resource files, resource names, route IDs, arguments, labels, mock credentials, bottom navigation behavior, and back-stack behavior unchanged.
- Do not modify `app/build.gradle.kts`, dependency versions, theme resources, layout structure, or menu resources for this package-only refactor. The only permitted layout edit is the non-runtime `tools:context` preview attribute if Android Studio requires it.
- Preserve the user's unrelated `.idea/deviceManager.xml` and `.idea/misc.xml` changes; do not stage or commit them.
- Use explicit root `R` imports (`com.example.mormeehing.R`) from subpackages where Java code references resources.
- Use the existing tests as regression coverage. Do not add production behavior merely to make a package move compile.

## Review Focus

- Launcher resolution: `AndroidManifest.xml` must resolve `com.example.mormeehing.core.navigation.MainActivity` and still launch the splash screen.
- Navigation class resolution: every `android:name` in `nav_graph.xml` must resolve to the new auth, jobs, or common package.
- Resource access from subpackages: `R` imports must compile and lint without changing the namespace or resource IDs.
- Test discovery: moved local and instrumentation tests must be found under their new fully qualified names.
- Runtime parity: existing instrumentation coverage must still prove splash/login, mock credentials, bottom navigation, job listings, FAB, title-only routes, and Back behavior.

## File Map

Production moves:

| Current file | New file | Package |
| --- | --- | --- |
| `app/src/main/java/com/example/mormeehing/MainActivity.java` | `app/src/main/java/com/example/mormeehing/core/navigation/MainActivity.java` | `com.example.mormeehing.core.navigation` |
| `app/src/main/java/com/example/mormeehing/LoginFragment.java` | `app/src/main/java/com/example/mormeehing/feature/auth/LoginFragment.java` | `com.example.mormeehing.feature.auth` |
| `app/src/main/java/com/example/mormeehing/SplashFragment.java` | `app/src/main/java/com/example/mormeehing/feature/auth/SplashFragment.java` | `com.example.mormeehing.feature.auth` |
| `app/src/main/java/com/example/mormeehing/MockAuthValidator.java` | `app/src/main/java/com/example/mormeehing/feature/auth/MockAuthValidator.java` | `com.example.mormeehing.feature.auth` |
| `app/src/main/java/com/example/mormeehing/SearchFragment.java` | `app/src/main/java/com/example/mormeehing/feature/jobs/SearchFragment.java` | `com.example.mormeehing.feature.jobs` |
| `app/src/main/java/com/example/mormeehing/JobListing.java` | `app/src/main/java/com/example/mormeehing/feature/jobs/JobListing.java` | `com.example.mormeehing.feature.jobs` |
| `app/src/main/java/com/example/mormeehing/ExampleJobListings.java` | `app/src/main/java/com/example/mormeehing/feature/jobs/ExampleJobListings.java` | `com.example.mormeehing.feature.jobs` |
| `app/src/main/java/com/example/mormeehing/JobListingAdapter.java` | `app/src/main/java/com/example/mormeehing/feature/jobs/JobListingAdapter.java` | `com.example.mormeehing.feature.jobs` |
| `app/src/main/java/com/example/mormeehing/PlaceholderFragment.java` | `app/src/main/java/com/example/mormeehing/feature/common/PlaceholderFragment.java` | `com.example.mormeehing.feature.common` |

Test moves:

| Current file | New file | Package |
| --- | --- | --- |
| `app/src/test/java/com/example/mormeehing/MockAuthValidatorTest.java` | `app/src/test/java/com/example/mormeehing/feature/auth/MockAuthValidatorTest.java` | `com.example.mormeehing.feature.auth` |
| `app/src/test/java/com/example/mormeehing/ExampleJobListingsTest.java` | `app/src/test/java/com/example/mormeehing/feature/jobs/ExampleJobListingsTest.java` | `com.example.mormeehing.feature.jobs` |
| `app/src/androidTest/java/com/example/mormeehing/ExampleInstrumentedTest.java` | `app/src/androidTest/java/com/example/mormeehing/core/navigation/ExampleInstrumentedTest.java` | `com.example.mormeehing.core.navigation` |
| `app/src/androidTest/java/com/example/mormeehing/StudentWorkHubNavigationTest.java` | `app/src/androidTest/java/com/example/mormeehing/core/navigation/StudentWorkHubNavigationTest.java` | `com.example.mormeehing.core.navigation` |

The generated template test `app/src/test/java/com/example/mormeehing/ExampleUnitTest.java` is removed after the real unit tests are confirmed to cover the maintained behavior. It is not a feature seam and would otherwise leave a flat-package artifact behind.

Integration files kept in place:

- `app/src/main/AndroidManifest.xml`: launcher activity class name only.
- `app/src/main/res/navigation/nav_graph.xml`: fragment class names only.
- `app/src/main/res/layout/activity_main.xml`: update only `tools:context` to the new activity package if Android Studio requires the preview context to resolve; runtime layout structure remains unchanged.

---

## Task 1: Establish a clean baseline and move the navigation host

**Files:**
- Move `app/src/main/java/com/example/mormeehing/MainActivity.java` to `app/src/main/java/com/example/mormeehing/core/navigation/MainActivity.java`.
- Move `app/src/androidTest/java/com/example/mormeehing/ExampleInstrumentedTest.java` to `app/src/androidTest/java/com/example/mormeehing/core/navigation/ExampleInstrumentedTest.java`.
- Move `app/src/androidTest/java/com/example/mormeehing/StudentWorkHubNavigationTest.java` to `app/src/androidTest/java/com/example/mormeehing/core/navigation/StudentWorkHubNavigationTest.java`.
- Update `app/src/main/AndroidManifest.xml`.
- Update `app/src/main/res/layout/activity_main.xml` preview context if needed.

- [ ] Before editing, run `.\gradlew.bat test`, `.\gradlew.bat :app:lintDebug`, and `.\gradlew.bat :app:assembleDebug` and record that the existing baseline passes. Run connected tests when an emulator/device is available.
- [ ] Move the three Java files while preserving file contents, then change their declarations to `package com.example.mormeehing.core.navigation;`.
- [ ] Add `import com.example.mormeehing.R;` to `MainActivity` and `StudentWorkHubNavigationTest` because the new packages no longer share the root package; `ExampleInstrumentedTest` does not reference resources and needs only its package declaration.
- [ ] Change the manifest activity name from `.MainActivity` to `.core.navigation.MainActivity`; keep exported state, launcher intent filter, theme, and soft-input behavior unchanged.
- [ ] Change `tools:context` from `.MainActivity` to `.core.navigation.MainActivity` only if required for preview resolution; do not change the `NavHostFragment` declaration or resource IDs.
- [ ] Run `.\gradlew.bat :app:testDebugUnitTest`, `.\gradlew.bat :app:lintDebug`, and `.\gradlew.bat :app:assembleDebug`.
- [ ] If those checks pass, commit only this task's source/test/manifest/layout changes with `refactor: move navigation host into core package`.

## Task 2: Move the authentication feature seam

**Files:**
- Move `LoginFragment.java`, `SplashFragment.java`, and `MockAuthValidator.java` into `app/src/main/java/com/example/mormeehing/feature/auth/`.
- Move `MockAuthValidatorTest.java` into `app/src/test/java/com/example/mormeehing/feature/auth/`.
- Update `app/src/main/res/navigation/nav_graph.xml` for the splash and login destinations.

- [ ] Move the three production files and change each declaration to `package com.example.mormeehing.feature.auth;`.
- [ ] Add `import com.example.mormeehing.R;` to `LoginFragment` and `SplashFragment`; keep `MockAuthValidator` free of resource coupling.
- [ ] Update `MockAuthValidatorTest` to `package com.example.mormeehing.feature.auth;` and import `MockAuthValidator` if required by the final package declaration.
- [ ] Change only these navigation class names in `nav_graph.xml`:

  ```xml
  android:name="com.example.mormeehing.feature.auth.SplashFragment"
  android:name="com.example.mormeehing.feature.auth.LoginFragment"
  ```

- [ ] Preserve splash/login route IDs, action IDs, login arguments, mock credential behavior, and error strings.
- [ ] Run `.\gradlew.bat :app:testDebugUnitTest --tests "com.example.mormeehing.feature.auth.MockAuthValidatorTest"`, then `.\gradlew.bat :app:lintDebug` and `.\gradlew.bat :app:assembleDebug`.
- [ ] Commit only this task's changes with `refactor: group authentication feature package`.

## Task 3: Move the job-search feature seam

**Files:**
- Move `SearchFragment.java`, `JobListing.java`, `ExampleJobListings.java`, and `JobListingAdapter.java` into `app/src/main/java/com/example/mormeehing/feature/jobs/`.
- Move `ExampleJobListingsTest.java` into `app/src/test/java/com/example/mormeehing/feature/jobs/`.
- Update `app/src/main/res/navigation/nav_graph.xml` for the search destination.

- [ ] Move the four production files and change each declaration to `package com.example.mormeehing.feature.jobs;`.
- [ ] Add `import com.example.mormeehing.R;` to `SearchFragment`, `JobListingAdapter`, and `ExampleJobListings` wherever resource IDs are referenced; keep `JobListing` focused on its existing model fields.
- [ ] Update `ExampleJobListingsTest` to `package com.example.mormeehing.feature.jobs;` and import the moved model/factory classes if required.
- [ ] Change the search destination class name to:

  ```xml
  android:name="com.example.mormeehing.feature.jobs.SearchFragment"
  ```

- [ ] Preserve the list construction through `ExampleJobListings.create()`, `RecyclerView` adapter usage, item ordering, resource-backed text, and existing search route ID.
- [ ] Run `.\gradlew.bat :app:testDebugUnitTest --tests "com.example.mormeehing.feature.jobs.ExampleJobListingsTest"`, then `.\gradlew.bat :app:lintDebug` and `.\gradlew.bat :app:assembleDebug`.
- [ ] Commit only this task's changes with `refactor: group job search feature package`.

## Task 4: Move the shared title-only feature seam

**Files:**
- Move `PlaceholderFragment.java` into `app/src/main/java/com/example/mormeehing/feature/common/`.
- Update every title-only destination in `app/src/main/res/navigation/nav_graph.xml`.

- [ ] Change the declaration to `package com.example.mormeehing.feature.common;`.
- [ ] Add `import com.example.mormeehing.R;` and update any moved-package references without changing the destination-title lookup logic.
- [ ] Replace `com.example.mormeehing.PlaceholderFragment` with `com.example.mormeehing.feature.common.PlaceholderFragment` for `homeFragment`, `createJobFragment`, `scheduleFragment`, `profileFragment`, `jobDetailsFragment`, `workHoursFragment`, `incomeFragment`, `applicationsFragment`, and `searchFiltersFragment`.
- [ ] Preserve all route IDs, title arguments, labels, bottom bar visibility decisions, and navigation actions.
- [ ] Run `.\gradlew.bat :app:lintDebug` and `.\gradlew.bat :app:assembleDebug`.
- [ ] Commit only this task's changes with `refactor: group shared placeholder feature package`.

## Task 5: Verify the complete package topology and runtime parity

**Files:**
- Review all files under `app/src/main/java/com/example/mormeehing/`, `app/src/test/java/com/example/mormeehing/`, and `app/src/androidTest/java/com/example/mormeehing/`.
- Review `app/src/main/AndroidManifest.xml` and `app/src/main/res/navigation/nav_graph.xml`.

- [ ] Run `rg -n "package com\.example\.mormeehing;|com\.example\.mormeehing\.(MainActivity|LoginFragment|SplashFragment|MockAuthValidator|SearchFragment|JobListing|ExampleJobListings|JobListingAdapter|PlaceholderFragment)" app/src --glob "*.java" --glob "*.xml"` and confirm no stale flat-package production/test/nav references remain; the root package may remain only for generated resources and files intentionally left at the root.
- [ ] Confirm the final directories match the spec's `core/navigation`, `feature/auth`, `feature/jobs`, and `feature/common` tree, with no `ExampleUnitTest.java` template artifact.
- [ ] Run `.\gradlew.bat test`.
- [ ] Run `.\gradlew.bat :app:lintDebug`.
- [ ] Run `.\gradlew.bat :app:assembleDebug`.
- [ ] Run `.\gradlew.bat :app:connectedDebugAndroidTest` on the available emulator/device. If the environment has no connected device, report that exact limitation instead of treating it as a passing result.
- [ ] Confirm the instrumentation suite still passes splash/login, valid and invalid mock credentials, Back behavior, bottom menu routes, example job listings, FAB routing, and title-only routes.
- [ ] Confirm `git status --short` shows only the intended refactor commits plus the pre-existing `.idea/deviceManager.xml` and `.idea/misc.xml` changes, which must remain uncommitted.
- [ ] Commit any final verification-only corrections separately as `refactor: finish feature-first package layout`; do not amend unrelated commits.

## Completion Criteria

- The production and test trees match the approved feature-first layout.
- The launcher and navigation graph resolve every moved class.
- `test`, `lintDebug`, and `assembleDebug` pass; connected instrumentation passes when a device is available.
- No screen, resource, route ID, theme, dependency, mock credential, bottom menu, list behavior, or Back behavior changes as a result of the refactor.
- The `.idea` changes remain outside the refactor history.
