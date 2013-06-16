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
import java.util.Date;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import ste.xtest.js.JavaScriptTest;

/**
 *
 * @author ste
 */
public class BugFreeItemFactory extends JavaScriptTest {

    Context cx = null;



    public BugFreeItemFactory() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.imageitem.js");
    }

    //
    // If type is provided in the item data, the created item shall be of the
    // specified type; otherwise an error shall be thrown.
    // If type is not provided the default options defaultType shll be used.
    //
    @Test
    public void itemFactory() throws Throwable {
        loadScript("src/test/javascript/itemtype1.js");

        assertEquals("box", exec("item1.getType()"));    // default
        assertEquals("image", exec("item2.getType()"));  // explicit type 1
        assertEquals("dot", exec("item3.getType()"));    // explicit type 2
        assertEquals("range", exec("item4.getType()"));  // range
    }

}