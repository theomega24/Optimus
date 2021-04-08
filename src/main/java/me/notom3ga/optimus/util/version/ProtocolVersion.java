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
            case 753:
                return "1.16.3";
            case 751:
                return "1.16.2";
            case 736:
                return "1.16.1";
            case 735:
                return "1.16";
            case 578:
                return "1.15.2";
            case 575:
                return "1.15.1";
            case 573:
                return "1.15";
            case 498:
                return "1.14.4";
            case 490:
                return "1.14.3";
            case 485:
                return "1.14.2";
            case 480:
                return "1.14.1";
            case 477:
                return "1.14";
            case 404:
                return "1.13.2";
            case 401:
                return "1.13.1";
            case 393:
                return "1.13";
            case 340:
                return "1.12.2";
            case 338:
                return "1.12.1";
            case 335:
                return "1.12";
            case 316:
                return "1.11.1/1.11.2";
            case 315:
                return "1.11";
            case 210:
                return "1.10/1.10.1/1.10.2";
            case 110:
                return "1.9.3/1.9.4";
            case 109:
                return "1.9.2";
            case 108:
                return "1.9.1";
            case 107:
                return "1.9";
            case 47:
                return "1.8/1.8.(1-9)";
            case 5:
                return "1.7.(6-10)";
            case 4:
                return "1.7.(2-5)";
            default:
                return "Unknown (" + version + ")";
        }
    }
}
