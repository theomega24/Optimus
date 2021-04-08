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

package me.notom3ga.optimus.util.version;

public class ProtocolVersion {
    public static String toString(int version) {
        switch (version) {
            case 754:
                return "1.16.4/1.16.5";
            case 340:
                return "1.12.2";
            case 47:
                return "1.8/1.8.(1-9)";
            default:
                return "Unknown (" + version + ")";
        }
    }
}
