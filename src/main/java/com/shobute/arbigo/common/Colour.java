/*
 * The MIT License
 *
 * Copyright 2015 Ben Lloyd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.shobute.arbigo.common;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 *
 * @author Ben Lloyd
 */
public class Colour {
    
    public static final String[] colours = new String[] {
        "Black", "White", "Red", "Blue", "Green", "Yellow", "Pink", "Cyan",
        "Orange", "Gray"
    };
    
    private static int id = 0;
    
    public static String getNextColour() {
        return colours[id++];
    }
    
    public static Color colourToColor(String colour) {
        Color color;
        try {
            Field field = Color.class.getField(colour.toUpperCase());
            color = (Color)field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            color = null; // Not defined
        }
        return color;
    }
    
}
