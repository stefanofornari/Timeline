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
import org.mozilla.javascript.UniqueTag;

import ste.xtest.js.JavaScriptTest;


/**
 *
 * @author ste
 */
public class BugFreeItemsByGroup extends JavaScriptTest {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeItemsByGroup() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
    }

    @Test
    public void getItemsByGroupWithPlainItems() throws Throwable {
        loadScript("src/test/javascript/stackwithgroups1.js");

        //
        // some real values
        //
        NativeObject ret = (NativeObject)exec("ret = timeline.getItemsByGroup(items);");
        NativeArray images = (NativeArray)ret.get("image", null);
        NativeArray files = (NativeArray)ret.get("file", null);
        NativeArray undefined = (NativeArray)ret.get("undefined", null);
        assertEquals(3, images.getLength());
        assertEquals("image1", exec("ret['image'][0].title"));
        assertEquals("image2", exec("ret['image'][1].title"));
        assertEquals("image3", exec("ret['image'][2].title"));
        assertEquals(4, files.getLength());
        assertEquals("file1", exec("ret['file'][0].title"));
        assertEquals("file2", exec("ret['file'][1].title"));
        assertEquals("file3", exec("ret['file'][2].title"));
        assertEquals("file4", exec("ret['file'][3].title"));
        assertEquals(1, undefined.getLength());
        assertEquals("file5", exec("ret['undefined'][0].title"));

        //
        // Empty array
        //
        ret = (NativeObject)exec("ret = timeline.getItemsByGroup([]);");
        assertEquals(UniqueTag.NOT_FOUND, ret.get("image", null));
    }

    /**
     * timeline.draw() processes the items so that their group becomes an object
     * where the name of the group goes in group.content.
     *
     * @throws Throwable
     */
    @Test
    public void getItemsByGroupWithGroupObjects() throws Throwable {
        loadScript("src/test/javascript/stackwithgroups2.js");

        //
        // some real values
        //
        NativeObject ret = (NativeObject)exec("ret = timeline.getItemsByGroup(data);");
        NativeArray images = (NativeArray)ret.get("image", null);
        NativeArray files = (NativeArray)ret.get("file", null);
        NativeArray undefined = (NativeArray)ret.get("undefined", null);
        assertEquals(3, images.getLength());
        assertEquals("image1", exec("ret['image'][0].title"));
        assertEquals("image2", exec("ret['image'][1].title"));
        assertEquals("image3", exec("ret['image'][2].title"));
        assertEquals(4, files.getLength());
        assertEquals("file1", exec("ret['file'][0].title"));
        assertEquals("file2", exec("ret['file'][1].title"));
        assertEquals("file3", exec("ret['file'][2].title"));
        assertEquals("file4", exec("ret['file'][3].title"));
        assertEquals(2, undefined.getLength());
        assertEquals("file5", exec("ret['undefined'][0].title"));

        //
        // Empty array
        //
        ret = (NativeObject)exec("ret = timeline.getItemsByGroup([]);");
        assertEquals(UniqueTag.NOT_FOUND, ret.get("image", null));
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/timeline.testitem.js");
        loadScript("src/test/javascript/stackwithgroups3.js");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        assertEquals(0, ret.getLength());
    }

    @Test
    public void stackCalculateFinalWithGroupsAndEmptyItemsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/timeline.testitem.js");
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; timeline.setOptions(options);");

        //
        // empty array first
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal({});");
        assertEquals(0, ret.getLength());
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/timeline.testitem.js");
        loadScript("src/test/javascript/stackwithgroups3.js");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        assertEquals(9, ret.getLength());

        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(85.0, exec("ret[0].top;"));
        assertEquals(40.0, exec("ret[0].item.group.itemsHeight;"));

        assertEquals("file1", exec("ret[1].item.title;"));
        assertEquals(85.0 , exec("ret[1].top;"));

        assertEquals("file3", exec("ret[2].item.title;"));
        assertEquals(85.0 , exec("ret[2].top;"));

        assertEquals("file2", exec("ret[3].item.title;"));
        assertEquals(95.0 , exec("ret[3].top;"));

        assertEquals("file4", exec("ret[4].item.title;"));
        assertEquals(105.0 , exec("ret[4].top;"));

        assertEquals("file6", exec("ret[5].item.title;"));
        assertEquals(85.0 , exec("ret[5].top;"));

        assertEquals("image1", exec("ret[6].item.title;"));
        assertEquals(135.0, exec("ret[6].top;"));
        assertEquals(30.0, exec("ret[6].item.group.itemsHeight;"));

        assertEquals("image2", exec("ret[7].item.title;"));
        assertEquals(145.0, exec("ret[7].top;"));

        assertEquals("image3", exec("ret[8].item.title;"));
        assertEquals(135.0, exec("ret[8].top;"));
    }

    @Test
    public void stackCalculateFinalWithGroupsWithCollisionsAxisAtBottom() throws Exception {
        loadScript("src/test/javascript/timeline.testitem.js");
        loadScript("src/test/javascript/stackwithgroups3.js");
        exec("options.axisOnTop = false; timeline.setOptions(options);");

        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items1);");
        assertEquals(9, ret.getLength());
        
        assertEquals("file5", exec("ret[0].item.title;"));
        assertEquals(1960.0, exec("ret[0].top;"));
        assertEquals(40.0, exec("ret[0].item.group.itemsHeight;"));

        assertEquals("file1", exec("ret[1].item.title;"));
        assertEquals(1960.0 , exec("ret[1].top;"));

        assertEquals("file3", exec("ret[2].item.title;"));
        assertEquals(1960.0 , exec("ret[2].top;"));

        assertEquals("file2", exec("ret[3].item.title;"));
        assertEquals(1950.0 , exec("ret[3].top;"));

        assertEquals("file4", exec("ret[4].item.title;"));
        assertEquals(1940.0 , exec("ret[4].top;"));

        assertEquals("file6", exec("ret[5].item.title;"));
        assertEquals(1960.0 , exec("ret[5].top;"));

        assertEquals("image1", exec("ret[6].item.title;"));
        assertEquals(1910.0, exec("ret[6].top;"));
        assertEquals(30.0, exec("ret[6].item.group.itemsHeight;"));

        assertEquals("image2", exec("ret[7].item.title;"));
        assertEquals(1900.0, exec("ret[7].top;"));

        assertEquals("image3", exec("ret[8].item.title;"));
        assertEquals(1910.0, exec("ret[8].top;"));
    }

    @Test
    public void stackCalculateFinalWithGroupsWithoutCollisionsAxisOnTop() throws Exception {
        loadScript("src/test/javascript/timeline.testitem.js");
        loadScript("src/test/javascript/stackwithgroups3.js");

        //
        // with items with collisions now
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.stackCalculateFinal(items2);");
        assertEquals(4, ret.getLength());
        assertEquals(20.0, exec("ret[0].item.group.itemsHeight;"));
        assertEquals(20.0, exec("ret[1].item.group.itemsHeight;"));
    }

}