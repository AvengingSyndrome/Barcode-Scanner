del bin\AndroidTest.unsigned.apk
aapt package -v -f -M AndroidManifest.xml -S res -I %android%\android.jar -F bin\AndroidTest.unsigned.apk bin