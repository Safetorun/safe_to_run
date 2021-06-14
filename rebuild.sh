cd /Users/danielllewellyn/IdeaProjects/safe_to_run/app/build/outputs/apk/debug &&
apktool.sh b app-debug && rm output.apk &&
zipalign  -v 4  app-debug/dist/app-debug.apk output.apk &&
apksigner sign --ks ~/.android/debug.keystore output.apk &&
adb install output.apk
