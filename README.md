# Minimal 24 — Watch Face Format face for OnePlus Watch (Wear OS 5+)

Pure-black digital face: 24h time with seconds, full day name and
DD/MM/YYYY date, battery ring (outer, turns red below 15%), a selectable
inner ring (time left in the day by default, step goal, heart rate, or off) with
its own colour, three fixed complications
(sunrise/sunset, steps, heart rate). Accent colour is
selectable in the watch's own face editor. Unread notifications use the
watch's own system dot.

## Build
No Gradle wrapper is checked in; use a system Gradle (9.x works) with the
Android SDK at `~/Android/Sdk` (set in `local.properties`):

    gradle :app:assembleDebug

The APK lands at `app/build/outputs/apk/debug/app-debug.apk`.

## Install on the watch
Use the SDK's adb (`~/Android/Sdk/platform-tools/adb`). Distro builds of
adb may lack mDNS, which you need to find the pairing port.

1. On the watch: Settings → Developer options → Wireless debugging (tap the
   build number 7× under About to unlock developer options). Re-check it
   after a reboot, and note the IP:port shown, which changes each time.
2. First time only, pair: tap "Pair new device" and keep that screen open.
   Its port is not the one shown on the main screen; find it with:

       adb mdns services        # look for _adb-tls-pairing._tcp
       adb pair <ip>:<pairing-port> <6-digit code>

3. Connect, install, and switch to the face:

       adb connect <ip>:<port>
       adb install -r app/build/outputs/apk/debug/app-debug.apk
       adb shell am broadcast -a com.google.android.wearable.app.DEBUG_SURFACE \
           --es operation set-watchface --es watchFaceId af.ass.watchface.minimal24

   If adb says "more than one device", the watch is listed twice (by IP
   and by mDNS name); add `-s <ip>:<port>`.

The last command is needed because OnePlus's face picker doesn't list
sideloaded faces. If you switch away, rerun it to switch back.

## Customise
Long-press the face → Customise: change the accent colour or the inner
ring. The complications are fixed; change their providers in
`watchface.xml`.

Seconds show while the screen is awake; Wear OS refreshes always-on mode
once a minute, so the dimmed layout shows HH:MM only, with the date and
complications dimmed.

Step goal is set with `STEP_GOAL` in `watchface.xml` (default 8000).
