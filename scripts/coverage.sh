./gradlew spotlessApply detekt build test\
    :safeToRunCore:jacocoTestReport :safeToRun:jacocoTestReportDebug \
    :safeToRun:generateJacocoBadge :safeToRunCore:generateJacocoBadge \
    :SafeToRunBackend:jacocoTestReport :SafeToRunBackend:generateJacocoBadge
#    :safeToRunInternal:jacocoTestReport :safeToRunInternal:generateJacocoBadge
