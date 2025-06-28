# WiFi Scanner App

## Overview
A simple Android application for scanning nearby WiFi networks.

## Development Setup
1. Ensure you have Android Studio installed
2. Clone the repository
3. Open the project in Android Studio

## Running Tests
### Unit Tests
- Run `./gradlew test` to execute unit tests
- Robolectric is used for Android unit testing

### Instrumentation Tests
- Run `./gradlew connectedAndroidTest` to run tests on a connected device or emulator

## Key Features
- WiFi network scanning
- Permission handling
- Simple, intuitive UI

## Testing Notes
- Test coverage focuses on core functionality
- Robolectric provides Android unit testing support
- Espresso used for UI interaction tests