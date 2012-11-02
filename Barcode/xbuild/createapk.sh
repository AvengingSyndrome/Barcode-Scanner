#!/bin/bash
rm ../bin/AndroidTest.unsigned.apk
aapt package -v -f -M ../AndroidManifest.xml -S ../res -I $ANDROID/android-16/android.jar -F ../bin/AndroidTest.unsigned.apk ../bin