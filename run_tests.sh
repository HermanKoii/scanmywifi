#!/bin/bash
# Custom test runner for Kotlin tests

# Compile Kotlin tests
kotlinc -cp "app/src/test/java:src/test/kotlin:path/to/junit.jar:path/to/mockito.jar" \
    src/test/kotlin/com/wifiscanner/utils/SignalStrengthInterpreterTest.kt \
    app/src/test/java/com/example/wifiscanner/WifiNetworkTest.kt \
    -d test_classes

# Run tests
java -cp "test_classes:path/to/junit.jar:path/to/mockito.jar" \
    org.junit.runner.JUnitCore \
    com.wifiscanner.utils.SignalStrengthInterpreterTest \
    com.example.wifiscanner.WifiNetworkTest