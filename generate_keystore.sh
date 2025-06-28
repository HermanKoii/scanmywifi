#!/bin/bash

# Keystore generation script for WiFi Scanner app
# WARNING: Replace values with your actual details

KEYSTORE_FILE="wifi_scanner_keystore.jks"
KEYSTORE_PASSWORD="your_secure_password"
KEY_ALIAS="wifi_scanner_key"
KEY_PASSWORD="your_secure_key_password"
VALIDITY_YEARS=25

keytool -genkeypair \
    -keystore "$KEYSTORE_FILE" \
    -storepass "$KEYSTORE_PASSWORD" \
    -keypass "$KEY_PASSWORD" \
    -alias "$KEY_ALIAS" \
    -keyalg RSA \
    -keysize 2048 \
    -validity $((VALIDITY_YEARS * 365)) \
    -dname "CN=WiFi Scanner App, OU=Development, O=YourCompany, L=YourCity, S=YourState, C=YourCountry"