# Fix App Crash on Startup

The app is crashing on startup with an `IllegalArgumentException: ending radius must be > 0` in `WashingMachineBottomBar.kt`. This occurs because a `RadialGradient` is initialized with a radius of 0 during the initial frame of the dome inflation animation.

Additionally, `startKoin` is incorrectly initialized in `MainActivity.onCreate`, which can lead to crashes during configuration changes or activity recreation.

## Proposed Changes

### [shared] Component

#### [MODIFY] [WashingMachineBottomBar.kt](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/shared/src/commonMain/kotlin/com/cafelavado/app/components/WashingMachineBottomBar.kt)
- Add a check to ensure `glowR > 0f` before calling `Brush.radialGradient` in `drawFooterBody`. This prevents the `IllegalArgumentException` on Android when the animation starts at 0.

### [androidApp] Component

#### [NEW] [MainApplication.kt](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/androidApp/src/main/kotlin/com/cafelavado/app/android/MainApplication.kt)
- Create a custom `Application` class to host Koin initialization.
- Move `startKoin` from `MainActivity` to `MainApplication.onCreate()`.
- Add `androidContext()` and `androidLogger()` to Koin initialization (requires adding `koin-android` dependency).

#### [MODIFY] [MainActivity.kt](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/androidApp/src/main/kotlin/com/cafelavado/app/android/MainActivity.kt)
- Remove `startKoin` block from `onCreate()`.

#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/androidApp/src/main/AndroidManifest.xml)
- Register `com.cafelavado.app.android.MainApplication` in the `<application>` tag.

#### [MODIFY] [build.gradle.kts (androidApp)](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/androidApp/build.gradle.kts)
- Add `koin-android` dependency.

#### [MODIFY] [libs.versions.toml](file:///C:/Users/rgome/Documents/Codes/Café Lavado (APP)/gradle/libs.versions.toml)
- Add `koin-android` library definition.

## Verification Plan

### Automated Tests
- Run `:androidApp:assembleDebug` to ensure the project still builds.

### Manual Verification
- Deploy the app to the `Medium Phone` emulator (or any Android emulator) and verify it starts without crashing.
- Verify that the bottom bar animation works as expected.
