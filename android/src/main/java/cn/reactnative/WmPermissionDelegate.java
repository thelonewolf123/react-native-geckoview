package cn.reactnative;

import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoResult;

public class WmPermissionDelegate implements GeckoSession.PermissionDelegate {
    private boolean allowProtectedContent = false;

    public void setAllowProtectedContent(boolean allow) {
        this.allowProtectedContent = allow;
    }

    @Override
    public GeckoResult<Integer> onContentPermissionRequest(
        GeckoSession session,
        ContentPermission perm
    ) {
        if (perm.permission == PERMISSION_MEDIA_KEY_SYSTEM_ACCESS) {
            if (allowProtectedContent) {
                return GeckoResult.fromValue(ContentPermission.VALUE_ALLOW);
            }
            return GeckoResult.fromValue(ContentPermission.VALUE_DENY);
        }
        return super.onContentPermissionRequest(session, perm);
    }
} 