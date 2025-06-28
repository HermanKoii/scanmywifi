#!/bin/bash
# Wrapper script for running Kotlin tests

# Ensure the script fails if any command fails
set -e

# Check if Gradle is available
if command -v ./gradlew &> /dev/null; then
    echo "Running tests with Gradle"
    ./gradlew test
elif command -v gradle &> /dev/null; then
    echo "Running tests with system Gradle"
    gradle test
else
    echo "No Gradle installation found. Please install Gradle."
    exit 1
fi