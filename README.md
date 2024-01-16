# Android Permission Handling Guide

## Overview

This guide provides insights into the behavior of permission requests on different Android versions.

### Android 9 and Below

**Permission Dialog:** Appears as many times as the user clicks on "Request Permission."
**Restrictions:** No restrictions on the number of permission dialog appearances.

### Android 10

**First Denial:** On the first denial, the second request will show "Allow," "Deny," and "Deny & don't ask again."
**Subsequent Requests:**
  - **Deny:** Dialog will be shown.
  - **Deny & don't ask:** Dialog will not be shown.

### Android 11 and Above

**Deny (twice):**
  - No dialog will be shown (activates "Don't ask again" behavior).
**Behavior:**
 
