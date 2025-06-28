#!/bin/bash
set -e

# Install Kotlin and Gradle
apt-get update
apt-get install -y openjdk-17-jdk kotlin gradle

# Run tests
gradle test