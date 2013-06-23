/*
 * Copyright (C) 2013 Stefano Fornari.
 * All Rights Reserved.  No use, copying or distribution of this
 * work may be made except in accordance with a valid license
 * agreement from Stefano Fornari.  This notice must be
 * included on all copies, modifications and derivatives of this
 * work.
 *
 * STEFANO FORNARI MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. STEFANO FORNARI SHALL NOT BE LIABLE FOR ANY
 * DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;
import static org.junit.Assert.*;

import ste.xtest.js.JavaScriptTest;


/**
 *
 * @author ste
 */
public class BugFreeJSONDate extends JavaScriptTest {

    public static final SimpleDateFormat DATE_ISO_UTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    static {
        DATE_ISO_UTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public BugFreeJSONDate() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
    }

    @Test
    public void parseZuluDate() throws Throwable {
        Date d = new Date(1370214019000L);

        String s = String.format(
                       "var d = links.Timeline.parseJSONDate('%s');",
                       DATE_ISO_UTC.format(d)
                   );
        exec(s);
        assertEquals(Double.valueOf(d.getTime()), exec("d.getTime();"));
    }

    @Test
    public void parseTimeMillis() throws Throwable {
        Date d = new Date(1370214019000L);

        String s = String.format(
                       "var d = links.Timeline.parseJSONDate(1370214019000);"
                   );
        exec(s);
        assertEquals(Double.valueOf(d.getTime()), exec("d.getTime();"));
    }

}