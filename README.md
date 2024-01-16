**till Android 9**
Permission dialog will appear how many times user clicks on request permission. No restrictions.


**Android 10**
On first time user denial, the second request will show Allow, Deny and Deny & don't ask again.
When user clicks on request permission again,
Deny                 -    dialog will be shown
Deny and don't ask   -    dialog will not be shown 


**Starting from Android 11**
Deny(twice)          -       no dialog will be shown(nothing but don't ask again behavior)

Starting from Android 11, when a user denies a particular permission request twice (by tapping 
"Deny" twice), the system interprets it as the user not wanting to grant that permission and 
activates the "Don't ask again" behavior. After this, subsequent permission requests for that 
particular permission won't show the system dialog, and the shouldShowRequestPermissionRationale 
method will return false for that permission.

If the user denies the permission twice without granting it, the app is expected to handle this 
situation gracefully by possibly guiding the user to the app settings where they can manually 
enable the permission.