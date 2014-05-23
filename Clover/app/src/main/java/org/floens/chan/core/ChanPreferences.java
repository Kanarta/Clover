/*
 * Clover - 4chan browser https://github.com/Floens/Clover/
 * Copyright (C) 2014  Floens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.floens.chan.core;

import android.content.SharedPreferences;

import org.floens.chan.ChanApplication;
import org.floens.chan.R;
import org.floens.chan.service.WatchService;
import org.floens.chan.utils.ThemeHelper;

public class ChanPreferences {
    private static SharedPreferences p() {
        return ChanApplication.getPreferences();
    }

    public static boolean getOpenLinkConfirmation() {
        return p().getBoolean("preference_open_link_confirmation", true);
    }

    public static String getDefaultName() {
        return p().getString("preference_default_name", "");
    }

    public static String getDefaultEmail() {
        return p().getString("preference_default_email", "");
    }

    public static boolean getDeveloper() {
        return p().getBoolean("preference_developer", false);
    }

    public static void setDeveloper(boolean developer) {
        p().edit().putBoolean("preference_developer", developer).commit();
    }

    public static String getImageSaveDirectory() {
        return "Clover";
    }

    public static boolean getWatchEnabled() {
        return p().getBoolean("preference_watch_enabled", false);
    }

    /**
     * This also calls updateRunningState on the PinnedService to start/stop the
     * service as needed.
     *
     * @param enabled
     */
    public static void setWatchEnabled(boolean enabled) {
        if (getWatchEnabled() != enabled) {
            p().edit().putBoolean("preference_watch_enabled", enabled).commit();
            WatchService.updateRunningState(ChanApplication.getInstance());
            ChanApplication.getPinnedManager().onPinsChanged();
        }
    }

    public static boolean getWatchBackgroundEnabled() {
        return p().getBoolean("preference_watch_background_enabled", true);
    }

    public static long getWatchBackgroundTimeout() {
        String number = p().getString("preference_watch_background_timeout", "0");
        return Integer.parseInt(number) * 1000L;
    }

    public static boolean getVideoAutoPlay() {
        return p().getBoolean("preference_autoplay", false);
    }

    public static boolean getPassEnabled() {
        return p().getBoolean("preference_pass_enabled", false);
    }

    public static void setPassEnabled(boolean enabled) {
        if (getPassEnabled() != enabled) {
            p().edit().putBoolean("preference_pass_enabled", enabled).commit();
        }
    }

    public static String getPassToken() {
        return p().getString("preference_pass_token", "");
    }

    public static String getPassPin() {
        return p().getString("preference_pass_pin", "");
    }

    public static void setPassId(String id) {
        p().edit().putString("preference_pass_id", id).commit();
    }

    public static String getPassId() {
        return p().getString("preference_pass_id", "");
    }

    public static String getTheme() {
        return p().getString("preference_theme", "light");
    }
}
