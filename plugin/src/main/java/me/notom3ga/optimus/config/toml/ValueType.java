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

package me.notom3ga.optimus.config.toml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class ValueType<T> {
    private static final List<ValueType<?>> internalValues = new ArrayList<>();
    public static final List<ValueType<?>> values = Collections.unmodifiableList(internalValues);

    public static final ValueType<Boolean> BOOL = new ValueType<Boolean>("BOOL") {
        @Override
        public Optional<Boolean> apply(String str) {
            if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
                return Optional.of(Boolean.parseBoolean(str));
            }
            return Optional.empty();
        }

        @Override
        public String serialize(Boolean value) {
            return Boolean.toString(value);
        }
    };
    public static final ValueType<Integer> INT = new ValueType<Integer>("INT") {
        @Override
        public Optional<Integer> apply(String str) {
            if ((((str.charAt(0) == '\u002d' /* - */ || str.charAt(0) == '\u002b' /* + */) && Character.isDigit(str.charAt(1))) || Character.isDigit(str.charAt(0))) && !str.contains(".")) {
                return Optional.of(Integer.parseInt(str));
            }
            return Optional.empty();
        }

        @Override
        public String serialize(Integer value) {
            return value.toString();
        }
    };
    public static final ValueType<Double> DOUBLE = new ValueType<Double>("DOUBLE") {
        @Override
        public Optional<Double> apply(String str) {
            if ((((str.charAt(0) == '\u002d' /* - */ || str.charAt(0) == '\u002b' /* + */) && Character.isDigit(str.charAt(1))) || Character.isDigit(str.charAt(0))) && str.contains(".")) {
                return Optional.of(Double.parseDouble(str));
            }
            return Optional.empty();
        }

        @Override
        public String serialize(Double value) {
            return value.toString();
        }
    };
    public static final ValueType<String> STRING = new ValueType<String>("STRING") {
        @Override
        public Optional<String> apply(String str) {
            if (str.length() >= 2 && str.startsWith("\"")) {
                if (!str.endsWith("\"")) {
                    throw new RuntimeException("Missing ending quote");
                }
                return Optional.of(str.substring(1, str.length() - 1));
            }
            return Optional.empty();
        }

        @Override
        public String serialize(String value) {
            return "\"" + value + "\"";
        }
    };
    public static final ValueType<List<TOML.Value<?>>> LIST = new ValueType<List<TOML.Value<?>>>("LIST") {
        @Override
        public Optional<List<TOML.Value<?>>> apply(String str) {
            return Optional.empty();
        }

        @Override
        public String serialize(List<TOML.Value<?>> values) {
            StringBuilder builder = new StringBuilder().append("[\n");
            for (TOML.Value<?> value : values) {
                builder.append("    ").append(value.serialize()).append(",\n");
            }
            builder.append("  ]");
            return builder.toString();
        }
    };

    private final String name;

    private ValueType(String name) {
        this.name = name;

        internalValues.add(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public abstract Optional<T> apply(String str);

    public abstract String serialize(T value);
}
