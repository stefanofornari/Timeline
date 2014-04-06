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
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.UniqueTag;
import ste.xtest.js.BugFreeJavaScript;



/**
 *
 * @author ste
 */
public class BugFreeItemsByGroup extends BugFreeJavaScript {

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
        then(images.getLength()).isEqualTo(3);
        then(exec("ret['image'][0].title")).isEqualTo("image1");
        then(exec("ret['image'][1].title")).isEqualTo("image2");
        then(exec("ret['image'][2].title")).isEqualTo("image3");
        then(files.getLength()).isEqualTo(4);
        then(exec("ret['file'][0].title")).isEqualTo("file1");
        then(exec("ret['file'][1].title")).isEqualTo("file2");
        then(exec("ret['file'][2].title")).isEqualTo("file3");
        then(exec("ret['file'][3].title")).isEqualTo("file4");
        then(undefined.getLength()).isEqualTo(1);
        then(exec("ret['undefined'][0].title")).isEqualTo("file5");

        //
        // Empty array
        //
        ret = (NativeObject)exec("ret = timeline.getItemsByGroup([]);");
        then(ret.get("image", null)).isEqualTo(UniqueTag.NOT_FOUND);
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
        then(images.getLength()).isEqualTo(3);
        then(exec("ret['image'][0].title")).isEqualTo("image1");
        then(exec("ret['image'][1].title")).isEqualTo("image2");
        then(exec("ret['image'][2].title")).isEqualTo("image3");
        then(files.getLength()).isEqualTo(4);
        then(exec("ret['file'][0].title")).isEqualTo("file1");
        then(exec("ret['file'][1].title")).isEqualTo("file2");
        then(exec("ret['file'][2].title")).isEqualTo("file3");
        then(exec("ret['file'][3].title")).isEqualTo("file4");
        then(undefined.getLength()).isEqualTo(2);
        then(exec("ret['undefined'][0].title")).isEqualTo("file5");

        //
        // Empty array
        //
        ret = (NativeObject)exec("ret = timeline.getItemsByGroup([]);");
        then(ret.get("image", null)).isEqualTo(UniqueTag.NOT_FOUND);
    }
}