./gradlew spotlessApply detekt build test\
    :safeToRunCore:jacocoTestReport :safeToRun:jacocoTestReportDebug \
    :safeToRun:generateJacocoBadge :safeToRunCore:generateJacocoBadge \
    :safeToRunInternal:jacocoTestReport :safeToRunInternal:generateJacocoBadge
