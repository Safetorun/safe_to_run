#!/bin/sh

./gradlew :SafeToRunBackend:build :SafeToRunBackend:shadowJar
sam package --template-file sam-template.yml --output-template-file sam-template-packages.yml --s3-bucket safe-to-run-lambda-bucket --profile my_amplify_profile
sam deploy --template-file sam-template-packages.yml --stack-name sam-safetorun-backend --capabilities CAPABILITY_IAM --profile my_amplify_profile