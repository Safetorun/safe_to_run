./gradlew spotlessApply detekt test koverReport \
 :safeToRunInputValidation:jacocoTestReportDebug \
 :safeToRunInternal:jacocoTestReport \
 :safeToRunCore:jacocoTestReport \
 :safeToRun:jacocoTestReportDebug \
 jacocoTestReport