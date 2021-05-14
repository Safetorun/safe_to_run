./gradlew shadowJar && sam local invoke -e Resources/safeToRunDevice.json -t sam-template-testing.yml SafeToRunBackend
