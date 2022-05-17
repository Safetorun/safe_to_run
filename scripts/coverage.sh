./gradlew spotlessApply detekt test \
 :safeToRunInputValidation:jacocoTestReportDebug \
 :safeToRunInternal:jacocoTestReport \
 :safeToRunCore:jacocoTestReport \
 :safeToRun:jacocoTestReportDebug \
 jacocoTestReport