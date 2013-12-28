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


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;

import ste.xtest.js.JavaScriptTest;


/**
 *
 * @author ste
 */
public class BugFreeItemsByGroupUngrouped extends JavaScriptTest {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeItemsByGroupUngrouped() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.testitem.js");
    }

    @Test
    public void initialItemsPositionForUngroupedItems() throws Throwable {
        loadScript("src/test/javascript/stackwithgroups4.js");

        //
        // some real values
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items);");
        assertEquals(9, ret.getLength());
        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(1970.0, exec("ret[0].top;"));
        assertEquals("image1", exec("ret[1].item.title;"));
        assertEquals(1960.0, exec("ret[1].top;"));
        assertEquals("image2", exec("ret[2].item.title;"));
        assertEquals(1950.0, exec("ret[2].top;"));
        assertEquals("file1", exec("ret[3].item.title;"));
        assertEquals(1970.0, exec("ret[3].top;"));
        assertEquals("image3", exec("ret[4].item.title;"));
        assertEquals(1970.0, exec("ret[4].top;"));
        assertEquals("file3", exec("ret[5].item.title;"));
        assertEquals(1960.0, exec("ret[5].top;"));
        assertEquals("file2", exec("ret[6].item.title;"));
        assertEquals(1950.0, exec("ret[6].top;"));
        assertEquals("file4", exec("ret[7].item.title;"));
        assertEquals(1940.0, exec("ret[7].top;"));
        assertEquals("file6", exec("ret[8].item.title;"));
        assertEquals(1970.0, exec("ret[8].top;"));
    }

    @Test
    public void initialItemsPositionForUngroupedItemsWithStackEventsOff() throws Throwable {
        loadScript("src/test/javascript/stackwithgroups4.js");
        exec("options.stackEvents = false; timeline.setOptions(options);");

        //
        // some real values
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items);");
        assertEquals(9, ret.getLength());
        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(1970.0, exec("ret[0].top;"));
        assertEquals("image1", exec("ret[1].item.title;"));
        assertEquals(1970.0, exec("ret[1].top;"));
        assertEquals("image2", exec("ret[2].item.title;"));
        assertEquals(1970.0, exec("ret[2].top;"));
        assertEquals("file1", exec("ret[3].item.title;"));
        assertEquals(1970.0, exec("ret[3].top;"));
        assertEquals("image3", exec("ret[4].item.title;"));
        assertEquals(1970.0, exec("ret[4].top;"));
        assertEquals("file3", exec("ret[5].item.title;"));
        assertEquals(1970.0, exec("ret[5].top;"));
        assertEquals("file2", exec("ret[6].item.title;"));
        assertEquals(1970.0, exec("ret[6].top;"));
        assertEquals("file4", exec("ret[7].item.title;"));
        assertEquals(1970.0, exec("ret[7].top;"));
        assertEquals("file6", exec("ret[8].item.title;"));
        assertEquals(1970.0, exec("ret[8].top;"));
    }

}