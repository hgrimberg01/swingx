/*
 * $Id$
 *
 * Copyright 2009 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package org.jdesktop.swingx.renderer;

import java.util.Calendar;
import java.util.Locale;

import org.jdesktop.swingx.InteractiveTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 
 */
@RunWith(JUnit4.class)
public class StringValuesIssues extends InteractiveTestCase {

    /**
     * Issue #1273-swingx: Locale-dependent StringValues constants 
     *    not updated on changing default.
     */
    @Test
    public void testLocaleDateToString() {
        Locale locale = Locale.getDefault();
        try {
            Calendar number = Calendar.getInstance();
            number.set(Calendar.MONTH, Calendar.DECEMBER);
            Locale.setDefault(Locale.GERMAN);
            String german = StringValues.DATE_TO_STRING.getString(number.getTime());
            assertTrue("formatted German " + german, german.indexOf(".") >= 0);
            Locale.setDefault(Locale.US);
            String us = StringValues.DATE_TO_STRING.getString(number.getTime());
            assertTrue("formatted us " + us, us.indexOf("Dec") >= 0);
        } finally {
            Locale.setDefault(locale);
        }
    }
    
    /**
     * Issue #1273-swingx: Locale-dependent StringValues constants 
     *    not updated on changing default.
     */
    @Test
    public void testLocaleNumberToString() {
        Locale locale = Locale.getDefault();
        try {
            float number = 10.5f;
            Locale.setDefault(Locale.US);
            String us = StringValues.NUMBER_TO_STRING.getString(number);
            assertTrue("formatted us " + us, us.indexOf(".") > 0);
            Locale.setDefault(Locale.GERMAN);
            String german = StringValues.NUMBER_TO_STRING.getString(number);
            assertTrue("formatted German " + german, german.indexOf(",") > 0);
        } finally {
            Locale.setDefault(locale);
        }
    }
    
    @Test
    public void testDummy() {
        // keep runner happy if we have not issues
    } 
    
}