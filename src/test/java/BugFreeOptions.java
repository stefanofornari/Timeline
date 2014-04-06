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


import java.util.HashMap;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Test;
import ste.xtest.js.BugFreeJavaScript;



/**
 *
 * @author ste
 */
public class BugFreeOptions extends BugFreeJavaScript {

    public static final String OPTION_MAX_CLUSTERING_ITEM = "maxClusterItems";

    public BugFreeOptions() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
    }

    @Test
    public void getAndSetOptions() throws Throwable {

        //
        // default options
        // ---------------
        //
        loadScript("src/test/javascript/options1.js");
        then(
            exec("timeline.getOptions();").getClass().getName()
        ).isEqualTo(org.mozilla.javascript.NativeObject.class.getName());

        //
        // NOTE: I do not include i18n properties as I do not think they should
        // be options
        //
        HashMap<String,Object> ref = new HashMap();
        ref.put("width", "100%");
        ref.put("height", "auto");
        ref.put("minHeight", 0.0);
        ref.put("autoHeight", Boolean.TRUE);
        ref.put("eventMargin", 10);
        ref.put("eventMarginAxis", 20);
        ref.put("dragAreaWidth", 10);
        ref.put("zoomMin", 10);
        ref.put("zoomMax", 3.1536E14);
        ref.put("moveable", Boolean.TRUE);
        ref.put("zoomable", Boolean.TRUE);
        ref.put("selectable", Boolean.TRUE);
        ref.put("editable", Boolean.FALSE);
        ref.put("snapEvents", Boolean.TRUE);
        ref.put("groupChangeable", Boolean.TRUE);
        ref.put("showCurrentTime", Boolean.TRUE);
        ref.put("showCustomTime", Boolean.FALSE);
        ref.put("showMajorLabels", Boolean.TRUE);
        ref.put("showMinorLabels", Boolean.TRUE);
        ref.put("showNavigation", Boolean.FALSE);
        ref.put("showButtonNew", Boolean.FALSE);
        ref.put("groupsOnRight", Boolean.FALSE);
        ref.put("axisOnTop", Boolean.FALSE);
        ref.put("stackEvents", Boolean.TRUE);
        ref.put("animate", Boolean.FALSE);
        ref.put("animateZoom", Boolean.FALSE);
        ref.put("cluster", Boolean.FALSE);
        ref.put("customStackOrder", Boolean.FALSE);
        ref.put("style", "box");
        ref.put("locale", "en");
        ref.put(OPTION_MAX_CLUSTERING_ITEM, 5);

        for (String key: ref.keySet()) {
            System.out.println("Checking " + key);
            then(exec(String.format("timeline.getOptions()['%s'];", key))).isEqualTo(ref.get(key));
        }

        then(
            exec("timeline.getOptions()['min'];").getClass().getName()
        ).isEqualTo(org.mozilla.javascript.Undefined.class.getName());
        then(
            exec("timeline.getOptions()['max'];").getClass().getName()
        ).isEqualTo(org.mozilla.javascript.Undefined.class.getName());

        //
        // set and get
        // -----------
        //

        //
        // Let's check just some basic plus the ones wich has some additional
        // logic
        //
        exec("timeline.setOptions({'width': '50%'});");
        ref.put("width", "50%");
        for (String key: ref.keySet()) {
            System.out.println("Cheking(2) " + key);
            then(exec(String.format("timeline.getOptions()['%s'];", key))).isEqualTo(ref.get(key));
        }
        exec("timeline.setOptions({'height': '50%'});");
        then(exec("timeline.getOptions()['height'];")).isEqualTo("50%");
        then(exec("timeline.getOptions()['autoHeight'];")).isEqualTo(false);
    }

}