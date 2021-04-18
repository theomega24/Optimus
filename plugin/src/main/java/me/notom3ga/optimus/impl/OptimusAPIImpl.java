/*
 * Optimus
 * Copyright (C) 2021 Ben Kerllenevich
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

package me.notom3ga.optimus.impl;

import me.notom3ga.optimus.api.OptimusAPI;
import me.notom3ga.optimus.api.check.CheckCategory;
import me.notom3ga.optimus.check.Check;
import me.notom3ga.optimus.user.User;
import me.notom3ga.optimus.user.UserManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class OptimusAPIImpl implements OptimusAPI {

    @Override
    public int getViolations(Player player, String name, String type) {
        for (Check check : UserManager.getUser(player).checks) {
            if (check.getName().equalsIgnoreCase(name) && check.getType().equalsIgnoreCase(type)) {
                return check.getVl();
            }
        }

        throw new IllegalArgumentException("Could not find a check by the name of " + name + type);
    }

    @Override
    public int getViolations(Player player, CheckCategory category) {
        User user = UserManager.getUser(player);

        switch (category) {
            case MOVEMENT:
                return user.getMovementVL();
            case PLAYER:
                return user.getPlayerVL();
            default:
                return user.getVL();
        }
    }

    @Override
    public int getViolations(Player player) {
        return UserManager.getUser(player).getVL();
    }

    @Override
    public Map<String, String> getChecks(Player player) {
        return new HashMap<>(){{
            UserManager.getUser(player).checks.forEach(check -> put(check.getName(), check.getType()));
        }};
    }

    @Override
    public boolean isExempt(Player player) {
        return UserManager.getUser(player).exempt;
    }

    @Override
    public void setExempt(Player player, boolean exempt) {
        UserManager.getUser(player).exempt = exempt;
    }

    @Override
    public boolean hasAlerts(Player player) {
        return UserManager.getUser(player).alerts;
    }

    @Override
    public void setAlerts(Player player, boolean alerts) {
        UserManager.getUser(player).alerts = alerts;
    }

    @Override
    public boolean isBedrock(Player player) {
        return UserManager.getUser(player).bedrock;
    }
}
