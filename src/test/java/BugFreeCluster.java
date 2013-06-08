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
import org.junit.Test;
import ste.xtest.js.JavaScriptTest;

/**
 *
 * @author ste
 */
public class BugFreeCluster extends JavaScriptTest {


    public BugFreeCluster() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
    }

    @Test
    public void cluster() throws Throwable {
        loadScript("src/test/json/test1.js");
        loadScript("src/test/javascript/cluster1.js");

    }
}