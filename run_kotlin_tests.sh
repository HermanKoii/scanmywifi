#!/bin/bash
# Compile and run Kotlin tests

# Ensure JUnit and Kotlin compiler are available
echo "Compiling tests..."
kotlinc -cp ".:app/src/test/java:src/test/kotlin:$JUNIT_JAR" \
    src/main/kotlin/com/wifiscanner/utils/SignalStrengthInterpreter.kt \
    app/src/main/java/com/example/wifiscanner/model/WifiNetwork.kt \
    src/test/kotlin/com/wifiscanner/utils/SignalStrengthInterpreterTest.kt \
    app/src/test/java/com/example/wifiscanner/WifiNetworkTest.kt \
    -d test_classes

# Run tests
echo "Running tests..."
java -cp "test_classes:$JUNIT_JAR" org.junit.runner.JUnitCore \
    com.wifiscanner.utils.SignalStrengthInterpreterTest \
    com.example.wifiscanner.WifiNetworkTest