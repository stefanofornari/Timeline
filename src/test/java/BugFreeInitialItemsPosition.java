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
public class BugFreeInitialItemsPosition extends JavaScriptTest {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeInitialItemsPosition() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
    }

    @Test
    public void getItemsByGroup() throws Throwable {
        loadScript("src/test/javascript/stackwithgroups1.js");

        //
        // some real values
        //
        NativeArray ret = (NativeArray)exec("ret = timeline.initialItemsPosition(timeline.items);");
        assertEquals(8, ret.getLength());
    }

}