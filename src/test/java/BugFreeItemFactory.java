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
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Test;
import org.mozilla.javascript.Context;
import ste.xtest.js.BugFreeJavaScript;

/**
 *
 * @author ste
 */
public class BugFreeItemFactory extends BugFreeJavaScript {

    Context cx = null;



    public BugFreeItemFactory() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.testitem.js");
    }

    //
    // If type is provided in the item data, the created item shall be of the
    // specified type; otherwise an error shall be thrown.
    // If type is not provided the default options defaultType shll be used.
    //
    @Test
    public void itemFactory() throws Throwable {
        loadScript("src/test/javascript/itemtype1.js");

        then(exec("item1.getType()")).isEqualTo("box");    // default
        then(exec("item2.getType()")).isEqualTo("image");  // explicit type 1
        then(exec("item3.getType()")).isEqualTo("dot");    // explicit type 2
        then(exec("item4.getType()")).isEqualTo("range");  // range
    }

}