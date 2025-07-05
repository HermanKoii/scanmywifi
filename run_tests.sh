#!/bin/bash
set -e

# Compile Kotlin sources
kotlinc src/main/kotlin/com/wifiscanner/utils/SignalStrengthInterpreter.kt \
        app/src/main/java/com/example/wifiscanner/model/WifiNetwork.kt \
        -cp .:./android-stubs.jar \
        -d compiled_classes

# Compile test sources
kotlinc src/test/kotlin/com/wifiscanner/utils/SignalStrengthInterpreterTest.kt \
        app/src/test/java/com/example/wifiscanner/WifiNetworkTest.kt \
        -cp .:./compiled_classes:./android-stubs.jar \
        -d compiled_tests

# Run tests
java -cp .:./compiled_classes:./compiled_tests:./junit.jar:./hamcrest.jar \
     org.junit.runner.JUnitCore \
     com.wifiscanner.utils.SignalStrengthInterpreterTest \
     com.example.wifiscanner.WifiNetworkTest