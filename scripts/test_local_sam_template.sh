./gradlew shadowJar &&
sam local invoke -e Resources/safeToRunDevice.json -t sam-template-testing.yml --profile my_amplify_profile SafeToRunBackend > signature.json &&
sam local invoke -e signature.json -t sam-template-testing.yml --profile my_amplify_profile SafeToRunBackendVerify
