# Android Native App Override

> This page overrides `design-system/student-work-hub/MASTER.md` for the Android Java/XML implementation.

## Stack

- Java + XML Views
- Material Components
- Android Navigation Component
- Light Theme first; keep semantic tokens ready for a future dark theme

## Token Mapping

Map the generated design-system tokens into Android resources. Do not put raw values in screen layouts.

| Role | Resource value |
|---|---|
| Primary | `#7C3AED` |
| On primary | `#FFFFFF` |
| Secondary | `#A78BFA` |
| Accent / success | `#16A34A` |
| App background | `#FAF5FF` |
| Card surface | `#FFFFFF` |
| Primary text | `#4C1D95` |
| Secondary text | `#475569` |
| Border | `#DDD6FE` |
| Error | `#DC2626` |

Store these in `colors.xml`, reusable spacing in `dimens.xml`, and component appearances in `themes.xml` / style resources.

## Layout Rules

- Use an 8dp spacing rhythm, with 4dp only for tight icon/text relationships.
- Use 16dp phone content gutters as the default.
- Keep every interactive target at least 48dp × 48dp, even when the visible icon is smaller.
- Apply system bar insets to content, headers, bottom navigation, and bottom CTA areas.
- Add bottom padding so scrollable content cannot disappear behind the fixed bottom menu.
- Use opaque cards/surfaces with clear separation from the background; avoid low-contrast translucent cards.
- Support long Thai labels without clipping or forced single-line truncation.

## Navigation Rules

- Keep one `MainActivity` as the navigation host.
- Use Navigation Component routes and preserve predictable Back behavior.
- Use no more than five top-level bottom navigation destinations.
- Keep Bottom Menu hidden on Splash and Login.
- Use the center `+` as a prominent action/FAB route to Create Job, not as an overloaded top-level tab.
- Do not let detail/placeholder pages create a second competing bottom menu.

## Login Form Rules

- Give every field a visible label; do not rely on hint text as the only label.
- Use `TextInputLayout` error text directly below the invalid field.
- Keep input values after failed submit.
- Allow paste and password-manager autofill.
- Provide a show/hide password action with a meaningful content description.
- Use the mock credentials only in the authentication logic, never in screen text or logs.

## Icon and Accessibility Rules

- Use Material/vector drawables from one consistent icon family; do not use emoji as icons.
- Give standalone icon buttons a localized `contentDescription`.
- Hide decorative icons when equivalent visible text already communicates the meaning.
- Expose selected state for the active bottom-menu item.
- Keep text and meaningful icons at a minimum 4.5:1 contrast against their surfaces.

## Interaction and Motion

- Use Material ripple/state layers for pressed feedback within roughly 80–150ms.
- Keep transitions subtle and avoid layout-shifting movement.
- Prefer stable elevation/color changes over resizing controls.
- Respect reduced-motion preferences; navigation must remain understandable without animation.

## Native Translation Notes

The web-oriented pattern and CSS examples in the generated master file are not used literally. For this native app, apply only the validated style, color, typography, spacing, accessibility, and interaction principles above.

