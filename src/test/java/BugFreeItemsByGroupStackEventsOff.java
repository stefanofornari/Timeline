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

import ste.xtest.js.JavaScriptTest;


/**
 *
 * @author ste
 */
public class BugFreeItemsByGroupStackEventsOff extends JavaScriptTest {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeItemsByGroupStackEventsOff() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.testitem.js");
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.stackEvents = false; timeline.setOptions(options);");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        assertEquals(0, ret.getLength());
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; options.stackEvents = false; timeline.setOptions(options);");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        assertEquals(0, ret.getLength());
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.stackEvents = false; timeline.setOptions(options);");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        assertEquals(9, ret.getLength());

        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(75.0, exec("ret[0].top;"));
        assertEquals(10.0, exec("ret[0].item.group.itemsHeight;"));

        assertEquals("file1", exec("ret[1].item.title;"));
        assertEquals(75.0 , exec("ret[1].top;"));

        assertEquals("file3", exec("ret[2].item.title;"));
        assertEquals(75.0 , exec("ret[2].top;"));

        assertEquals("file2", exec("ret[3].item.title;"));
        assertEquals(75.0 , exec("ret[3].top;"));

        assertEquals("file4", exec("ret[4].item.title;"));
        assertEquals(75.0 , exec("ret[4].top;"));

        assertEquals("file6", exec("ret[5].item.title;"));
        assertEquals(75.0 , exec("ret[5].top;"));

        assertEquals("image1", exec("ret[6].item.title;"));
        assertEquals(95.0, exec("ret[6].top;"));
        assertEquals(10.0, exec("ret[6].item.group.itemsHeight;"));

        assertEquals("image2", exec("ret[7].item.title;"));
        assertEquals(95.0, exec("ret[7].top;"));

        assertEquals("image3", exec("ret[8].item.title;"));
        assertEquals(95.0, exec("ret[8].top;"));
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; options.stackEvents = false; timeline.setOptions(options);");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        assertEquals(9, ret.getLength());

        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(1970.0, exec("ret[0].top;"));
        assertEquals(10.0, exec("ret[0].item.group.itemsHeight;"));

        assertEquals("file1", exec("ret[1].item.title;"));
        assertEquals(1970.0 , exec("ret[1].top;"));

        assertEquals("file3", exec("ret[2].item.title;"));
        assertEquals(1970.0 , exec("ret[2].top;"));

        assertEquals("file2", exec("ret[3].item.title;"));
        assertEquals(1970.0 , exec("ret[3].top;"));

        assertEquals("file4", exec("ret[4].item.title;"));
        assertEquals(1970.0 , exec("ret[4].top;"));

        assertEquals("file6", exec("ret[5].item.title;"));
        assertEquals(1970.0 , exec("ret[5].top;"));

        assertEquals("image1", exec("ret[6].item.title;"));
        assertEquals(1950.0, exec("ret[6].top;"));
        assertEquals(10.0, exec("ret[6].item.group.itemsHeight;"));

        assertEquals("image2", exec("ret[7].item.title;"));
        assertEquals(1950.0, exec("ret[7].top;"));

        assertEquals("image3", exec("ret[8].item.title;"));
        assertEquals(1950.0, exec("ret[8].top;"));
    }

    @Test
    public void stackCalculateFinalWithGroupsWithoutCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.stackEvents = false; timeline.setOptions(options);");

        //
        // with items with collisions now
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items2);");
        assertEquals(4, ret.getLength());
        assertEquals(10.0, exec("ret[0].item.group.itemsHeight;"));
        assertEquals(10.0, exec("ret[1].item.group.itemsHeight;"));
    }
}