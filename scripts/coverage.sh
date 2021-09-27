./gradlew spotlessApply detekt build test\
    :safeToRunCore:jacocoTestReport :safeToRun:jacocoTestReportDebug \
    :safeToRun:generateJacocoBadge :safeToRunCore:generateJacocoBadge \
    :safeToRunInputValidation:testDebugUnitTestCoverage :safeToRunInputValidation:generateJacocoBadge
