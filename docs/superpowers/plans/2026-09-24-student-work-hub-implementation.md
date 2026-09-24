# Student Work Hub Android UI Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use `superpowers:subagent-driven-development` or `superpowers:executing-plans` to implement this plan task-by-task. Steps use checkbox syntax for tracking.

**Goal:** Build the first working Student Work Hub Android flow from Splash to mock Login to a title-based Main shell with a Material-style bottom menu.

**Architecture:** Keep one `MainActivity` as the Navigation Component host. Use XML Fragment destinations for Splash, Login, and post-login placeholder pages; keep the bottom app bar and center FAB in the activity shell so the menu is shared and hidden on Splash/Login. Keep mock authentication in a small, unit-tested Java validator separate from UI code.

**Tech Stack:** Java 11, XML Views, AndroidX Activity/AppCompat/ConstraintLayout, Material Components 1.14.0, AndroidX Navigation Fragment/UI/Testing 2.10.2, JUnit 4, Espresso.

**Spec:** `docs/superpowers/specs/2026-09-24-student-work-hub-design.md`

## Global Constraints

- Both Splash buttons navigate to the same Login screen.
- Mock credentials are Email `student@example.com` and Password `password`.
- Login succeeds only when both values match the mock credentials.
- Google Login, Apple Login, third-party login, Backend, API, database, and persistent sessions are out of scope.
- Keep Java + XML; do not migrate the project to Kotlin or Compose.
- Use one Activity, Navigation Component, and Fragments.
- Use Light Theme as the primary visual direction, with semantic night tokens kept valid through the existing DayNight theme.
- Put user-facing text in `strings.xml`, colors in `colors.xml`/`colors-night.xml`, repeated dimensions in `dimens.xml`, and component appearances in theme/style resources.
- Use vector drawables for icons, meaningful `contentDescription` values for standalone icon controls, and no emoji icons.
- Keep Android interactive targets at least 48dp × 48dp and preserve system-bar/bottom-bar insets.
- Use no more than five top-level bottom-menu destinations; the center `+` is a FAB action to Create Job.
- Do not use the web-only Hero/Testimonials pattern from the generated design output for native screen structure.

## Review Focus

- Splash action symmetry: both `เริ่มใช้งาน` and `เข้าสู่ระบบ` must reach Login; instrumentation covers both buttons.
- Authentication edge cases: blank email, blank password, wrong credentials, and exact mock credentials; unit tests cover each validator result.
- Navigation history: successful Login removes Splash/Login from the back stack and bottom-menu routes remain predictable; instrumentation covers success and Back.
- Resource discipline: no invalid XML attributes, raw user-facing strings, raw colors, or missing content descriptions; `assembleDebug` and build output are reviewed.
- Mobile accessibility: 48dp targets, visible labels, inline field errors, password visibility, paste/autofill, safe-area padding, and readable Thai labels.

## File Map

### Modify

- `gradle/libs.versions.toml` — add Navigation 2.10.2 aliases.
- `app/build.gradle.kts` — add Navigation Fragment/UI/Testing dependencies.
- `app/src/main/java/com/example/mormeehing/MainActivity.java` — own the NavHost, shared BottomNavigationView/FAB, visibility, and route selection.
- `app/src/main/res/layout/activity_main.xml` — replace the invalid starter layout with the NavHost + bottom shell.
- `app/src/main/res/values/strings.xml` — add screen labels, menu labels, errors, and mock credentials.
- `app/src/main/res/values/colors.xml` and `values-night/colors.xml` — add semantic light/night tokens.
- `app/src/main/res/values/dimens.xml` — add the 4/8dp rhythm and 48dp controls.
- `app/src/main/res/values/themes.xml` and `values-night/themes.xml` — apply Material theme tokens.
- `app/src/main/res/navigation/nav_graph.xml` — add destinations and actions.
- `app/src/androidTest/java/com/example/mormeehing/ExampleInstrumentedTest.java` — replace the template test if it duplicates the new smoke test.

### Create

- `app/src/main/java/com/example/mormeehing/MockAuthValidator.java`
- `app/src/main/java/com/example/mormeehing/SplashFragment.java`
- `app/src/main/java/com/example/mormeehing/LoginFragment.java`
- `app/src/main/java/com/example/mormeehing/PlaceholderFragment.java`
- `app/src/main/res/layout/fragment_splash.xml`
- `app/src/main/res/layout/fragment_login.xml`
- `app/src/main/res/layout/fragment_placeholder.xml`
- `app/src/main/res/values/styles.xml`
- `app/src/main/res/menu/menu_bottom_app_bar.xml`
- `app/src/main/res/drawable/bg_splash.xml`
- `app/src/main/res/drawable/ic_home.xml`
- `app/src/main/res/drawable/ic_search.xml`
- `app/src/main/res/drawable/ic_calendar.xml`
- `app/src/main/res/drawable/ic_person.xml`
- `app/src/main/res/drawable/ic_add.xml`
- `app/src/main/res/drawable/ic_school.xml`
- `app/src/test/java/com/example/mormeehing/MockAuthValidatorTest.java`
- `app/src/androidTest/java/com/example/mormeehing/StudentWorkHubNavigationTest.java`

---

### Task 1: Unblock the baseline and install navigation/theme foundations

**Interfaces:** Produces Navigation dependencies for `MainActivity`/`nav_graph.xml` and semantic resources for every later layout.

- [ ] **Step 1: Reproduce the current baseline failure**

Run:

```powershell
.\gradlew.bat test
```

Expected before this task: resource linking fails because `activity_main.xml` contains `android:foreground=""` and `android:textColor="FFFFFF"`.

- [ ] **Step 2: Add Navigation 2.10.2**

In `gradle/libs.versions.toml`, add:

```toml
navigation = "2.10.2"
navigationFragment = { group = "androidx.navigation", name = "navigation-fragment", version.ref = "navigation" }
navigationUi = { group = "androidx.navigation", name = "navigation-ui", version.ref = "navigation" }
navigationTesting = { group = "androidx.navigation", name = "navigation-testing", version.ref = "navigation" }
```

In `app/build.gradle.kts`, add `implementation(libs.navigation.fragment)`, `implementation(libs.navigation.ui)`, and `androidTestImplementation(libs.navigation.testing)`. The version is based on the official AndroidX Navigation release notes: https://developer.android.com/jetpack/androidx/releases/navigation.

- [ ] **Step 3: Add semantic resources**

Use `design-system/student-work-hub/MASTER.md` and `design-system/student-work-hub/pages/android-app.md` as the source. Add light and night values for `color_primary`, `color_on_primary`, `color_secondary`, `color_accent`, `color_background`, `color_surface`, `color_on_surface`, `color_on_surface_variant`, `color_outline`, `color_error`, and `color_on_error`.

Create `dimens.xml` with the reusable 4/8dp rhythm, including:

```xml
<dimen name="space_xs">4dp</dimen>
<dimen name="space_sm">8dp</dimen>
<dimen name="space_md">16dp</dimen>
<dimen name="space_lg">24dp</dimen>
<dimen name="screen_gutter">16dp</dimen>
<dimen name="control_min_height">48dp</dimen>
<dimen name="icon_size">24dp</dimen>
<dimen name="bottom_bar_height">80dp</dimen>
```

Add resource-backed labels for Splash, Login, Home, Search, Create Job, Schedule, Profile, errors, menu items, `content_description_add`, and non-translatable `mock_user_email`/`mock_user_password`.

- [ ] **Step 4: Fix starter XML and apply Material theme**

Remove the invalid attributes from `activity_main.xml`; the shell task replaces the complete layout. Set Material `colorPrimary`, `colorSecondary`, `colorSurface`, `colorOnSurface`, `colorError`, and `android:fontFamily` from resources. Keep `Theme.Material3.DayNight.NoActionBar` so night tokens remain valid.

- [ ] **Step 5: Verify and commit**

Run `.\gradlew.bat test`; expected: existing unit tests pass and resource linking is clean. Then commit:

```powershell
git add gradle/libs.versions.toml app/build.gradle.kts app/src/main
git commit -m "build: add navigation and material resource foundations"
```

### Task 2: Implement mock authentication with TDD

**Interfaces:** Create `MockAuthValidator(String expectedEmail, String expectedPassword)` and `ValidationError validate(String email, String password)`, where the enum values are `NONE`, `EMAIL_REQUIRED`, `PASSWORD_REQUIRED`, and `INVALID_CREDENTIALS`.

- [ ] **Step 1: Write failing tests first**

Create `MockAuthValidatorTest.java` with these behaviors:

```java
@Test
public void acceptsExactMockCredentials() {
    MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");
    assertEquals(MockAuthValidator.ValidationError.NONE,
            validator.validate("student@example.com", "password"));
}

@Test
public void rejectsBlankEmailBeforeCredentialComparison() {
    MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");
    assertEquals(MockAuthValidator.ValidationError.EMAIL_REQUIRED,
            validator.validate("  ", "password"));
}

@Test
public void rejectsBlankPasswordBeforeCredentialComparison() {
    MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");
    assertEquals(MockAuthValidator.ValidationError.PASSWORD_REQUIRED,
            validator.validate("student@example.com", ""));
}

@Test
public void rejectsWrongCredentials() {
    MockAuthValidator validator = new MockAuthValidator("student@example.com", "password");
    assertEquals(MockAuthValidator.ValidationError.INVALID_CREDENTIALS,
            validator.validate("student@example.com", "wrong"));
}
```

- [ ] **Step 2: Verify RED**

Run `.\gradlew.bat test --tests "com.example.mormeehing.MockAuthValidatorTest"`.

Expected: compilation fails because `MockAuthValidator` does not exist yet. If it passes, correct the test before implementing.

- [ ] **Step 3: Implement the minimal pure-Java validator**

Trim email for whitespace handling, do not trim the password, compare against constructor values, and return the enum result. Use no Android imports and never log credentials.

- [ ] **Step 4: Verify GREEN and the full unit suite**

Run the focused test, then `.\gradlew.bat test`. Expected: all validator and existing tests pass.

- [ ] **Step 5: Commit**

```powershell
git add app/src/main/java/com/example/mormeehing/MockAuthValidator.java app/src/test/java/com/example/mormeehing/MockAuthValidatorTest.java
git commit -m "feat: add mock authentication validator"
```

### Task 3: Build the navigation shell and title-only destinations

**Interfaces:** `MainActivity` owns `nav_host`, `bottom_app_bar`, and `create_job_fab`. `PlaceholderFragment` reads the string argument `title` and displays it. The graph contains `splashFragment`, `loginFragment`, `homeFragment`, `searchFragment`, `createJobFragment`, `scheduleFragment`, and `profileFragment`.

- [ ] **Step 1: Write navigation smoke tests before shell behavior**

Create `StudentWorkHubNavigationTest` with `ActivityScenarioRule<MainActivity>`. Assert the initial Splash title, then assert that clicking each of `action_get_started` and `action_login` reaches `title_login`. These tests must fail against the starter layout because the expected navigation views do not exist.

- [ ] **Step 2: Run RED**

```powershell
.\gradlew.bat connectedDebugAndroidTest --tests "com.example.mormeehing.StudentWorkHubNavigationTest"
```

Expected: the test cannot find the expected Splash/Login content. If no emulator exists, record that environment limitation and run after an emulator is available; do not replace it with a weaker unit assertion.

- [ ] **Step 3: Add the graph and placeholder layout**

Set Splash as `startDestination`, add both Splash-to-Login actions, and give each post-login destination a resource-backed `title` string argument. Inflate `fragment_placeholder.xml`, read `getArguments().getString("title")`, and set the title TextView with a resource-backed fallback.

- [ ] **Step 4: Add the BottomNavigationView shell**

Replace `activity_main.xml` with a full-screen NavHost, a bottom-anchored BottomNavigationView, and a centered FAB. The menu has four checkable items: Home, Search, Schedule, Profile, with a disabled transparent center slot reserved for the FAB. The FAB routes to Create Job. Use vector icons and resource-backed labels/content descriptions.

- [ ] **Step 5: Implement MainActivity route handling**

Connect menu clicks to destination IDs, connect the FAB to Create Job, and add a destination listener. Show the bottom shell only on post-login destinations; hide it on Splash/Login; mark the current menu item selected. Use Navigation options that preserve predictable Back behavior and do not create duplicate top-level destinations.

- [ ] **Step 6: Run the shell build and keep the expected RED test visible**

Run `.\gradlew.bat assembleDebug` and confirm the APK compiles. The focused instrumentation test is intentionally still RED because Task 4 supplies the real Splash/Login fragments; do not call the full navigation flow green until Task 4.

- [ ] **Step 7: Commit**

```powershell
git add app/src/main/java app/src/main/res app/src/androidTest
git commit -m "feat: add navigation shell and bottom app bar"
```

### Task 4: Implement the Splash-to-Login-to-Home vertical slice

**Interfaces:** `SplashFragment` exposes two buttons that call the same Login navigation action. `LoginFragment` builds `MockAuthValidator` from the two mock resource strings, maps validation results to Material field errors, and navigates to Home with Splash/Login removed on success.

- [ ] **Step 1: Add failing instrumentation behaviors**

Add tests for exact mock credentials, wrong credentials, and post-login Back. The success path must assert `title_home` and `menu_home`; the wrong path must assert `error_invalid_credentials` and that `title_login` remains visible.

- [ ] **Step 2: Verify RED**

Run the focused instrumentation test. Expected: assertions fail because Splash/Login are still placeholders and the form does not exist.

- [ ] **Step 3: Implement Splash**

Use `bg_splash.xml`, `ic_school`, title, subtitle, and two full-width Material buttons. Both buttons must have at least `@dimen/control_min_height` and navigate to the same Login destination. Do not package the composite reference sheet `resources/content.png` as runtime artwork.

- [ ] **Step 4: Implement Login XML**

Use a scrollable Material form with visible labels, outlined `TextInputLayout` fields, email/password input types, password visibility toggle, autofill hints, and a full-width Login button. Keep the form above the IME using the existing `adjustResize` setting and resource-backed spacing.

- [ ] **Step 5: Implement Login behavior**

Call the validator once on submit. Map `EMAIL_REQUIRED` and `PASSWORD_REQUIRED` to the corresponding field errors; map `INVALID_CREDENTIALS` to a generic inline form error; clear a field error when that field changes. On `NONE`, navigate to Home with `popUpTo(splashFragment, inclusive=true)` and `launchSingleTop=true`.

- [ ] **Step 6: Verify GREEN and commit**

Run the focused instrumentation test. Expected: both Splash buttons, mock success, invalid credentials, bottom-menu visibility, and post-login Back behavior pass. Commit with message `feat: add splash and mock login flow`.

### Task 5: Add secondary title routes and complete verification

- [ ] **Step 1: Add secondary title resources/routes**

Add resource-backed placeholder titles for รายละเอียดงาน, ชั่วโมงทำงาน, รายได้, การสมัครงาน, and ตัวกรองการค้นหา. Route them through `PlaceholderFragment`; keep the bottom shell behavior explicit for whether each route is inside or outside Main.

- [ ] **Step 2: Add route/accessibility assertions before adjusting behavior**

Extend the instrumentation test to click Home, Search, FAB, Schedule, and Profile; assert each title and selected menu item. Assert the FAB has `content_description_add` and a clickable bounds size of at least 48dp.

- [ ] **Step 3: Run complete verification**

Run `.\gradlew.bat test`, `.\gradlew.bat assembleDebug`, and `.\gradlew.bat connectedDebugAndroidTest`. Expected: unit tests pass, the debug APK assembles, and instrumentation tests pass. If an emulator is unavailable, report that separately from source failures.

- [ ] **Step 4: Review native UI**

Inspect small-phone portrait, large-phone portrait, and landscape. Confirm safe-area padding, no content behind BottomNavigationView, Thai labels do not clip, icons are vector/consistent, light/night contrast is readable, password paste/autofill works, and pressed states do not shift layout.

- [ ] **Step 5: Commit verification**

```powershell
git add app/src/main app/src/test app/src/androidTest
git commit -m "test: verify student work hub navigation flow"
```

## Plan Self-Review

- Spec coverage: Splash, Login, mock validation, Main bottom menu, title-only pages, Theme/resources, no third-party login, and build/test acceptance criteria map to Tasks 1–5.
- Placeholder scan: no unfinished marker or undefined implementation step remains; all classes, resource names, commands, and test behaviors are named.
- Interface consistency: `MockAuthValidator` and its enum are defined in Task 2 and consumed by LoginFragment in Task 4; destination IDs and the `title` argument are defined in Task 3 and consumed by MainActivity/PlaceholderFragment.
- Review focus coverage: each listed failure mode has a corresponding unit, instrumentation, build, or inspection check.
- Scope check: all tasks belong to the approved UI/navigation slice; Backend and real authentication remain excluded.
