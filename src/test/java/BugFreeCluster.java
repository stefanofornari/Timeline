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
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import ste.xtest.js.BugFreeJavaScript;

/**
 *
 * @author ste
 */
public class BugFreeCluster extends BugFreeJavaScript {

    Context cx = null;

    @Before
    public void setUp() {
        Context.enter();
    }

    @After
    public void tearDown() {
        Context.exit();
    }


    public BugFreeCluster() throws Exception {
        loadScript("src/main/javascript/timeline/timeline.js");
        loadScript("src/test/javascript/timeline.testitem.js");
    }

    //
    // We do some legacy tests to be confident we do not break anything.
    //
    @Test
    public void legacyChecks() throws Throwable {
        loadScript("src/test/json/test1.js");
        loadScript("src/test/javascript/cluster1.js");

        //
        // granularity is fine enough not to have any item clusterd
        //
        NativeArray clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.02, 5)");
        then(clusters.getLength()).isZero();

        //
        // We now start to cluster
        //
        clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.004, 5)");
        then(clusters.getLength()).isEqualTo(7);

        checkCluster(
            new long[] {
                1368310820000L, 1368310823000L
            },
            (NativeObject)clusters.get(0, null)
        );

        checkCluster(
            new long[] {
                1368310842000L, 1368310850000L, 1368310854000L
            },
            (NativeObject)clusters.get(1, null)
        );

        checkCluster(
            new long[] {
                1368311035000L, 1368311038000L
            },
            (NativeObject)clusters.get(2, null)
        );

        checkCluster(
            new long[] {
                1368311117000L, 1368311118000L, 1368311119000L,
                1368311120000L, 1368311125000L, 1368311126000L
            },
            (NativeObject)clusters.get(3, null)
        );

        checkCluster(
            new long[] {
                1368311130000L, 1368311132000L
            },
            (NativeObject)clusters.get(4, null)
        );

        checkCluster(
            new long[] {
                1368311169000L, 1368311178000L, 1368311179000L
            },
            (NativeObject)clusters.get(5, null)
        );

        checkCluster(
            new long[] {
                1368311183000L, 1368311184000L, 1368311187000L
            },
            (NativeObject)clusters.get(6, null)
        );

        //
        // Zooming in
        //
        clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.005, 5)");
        then(clusters.getLength()).isEqualTo(3);

        checkCluster(
            new long[] {
                1368311118000L, 1368311119000L
            },
            (NativeObject)clusters.get(0, null)
        );

        checkCluster(
            new long[] {
                1368311125000L, 1368311126000L, 1368311127000L, 1368311129000L
            },
            (NativeObject)clusters.get(1, null)
        );

        checkCluster(
            new long[] {
                1368311183000L, 1368311184000L, 1368311187000L
            },
            (NativeObject)clusters.get(2, null)
        );

        //
        // Zooming in again
        //
        clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.01, 5)");
        then(clusters.getLength()).isEqualTo(1);

        checkCluster(
            new long[] {
                1368311129000L, 1368311130000L
            },
            (NativeObject)clusters.get(0, null)
        );
    }

    //
    // Note that we rely on legacyChecks to check the composition of the
    // clusters. Here we just make sure that we change that changing the options
    // the number of clusters changes accordingly.
    //
    @Test
    public void changeMaxClusterItems() throws Throwable {
        loadScript("src/test/json/test1.js");
        loadScript("src/test/javascript/cluster2.js");

        NativeArray clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.004, 3)");
        then(clusters.getLength()).isEqualTo(15);

        loadScript("src/test/javascript/cluster3.js");

        clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.004, 0)");
        then(clusters.getLength()).isEqualTo(18);
    }

    //
    // cluster items must have type set to cluster so that they can then be
    // rendered in a generic way.
    //
    @Test
    public void itemtTypeMustBeCluster() throws Throwable {
        loadScript("src/test/json/test1.js");
        loadScript("src/test/javascript/cluster4.js");

        NativeArray clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.005, 5)");
        then(clusters.getLength()).isEqualTo(3);
        for (int i=0; i<clusters.getLength(); ++i) {
            NativeObject cluster = (NativeObject)clusters.get(i, null);
            then(cluster.get("type", null)).isEqualTo("cluster");
        }
    }
    
    //
    // The 'start' property of a cluster shall be the earliest of the cluster
    //
    @Test
    public void startIsTheEarliest() throws Throwable {
        final long[] TEST_CLUSTER_STARTS = {
            1330885644000L, 1368133699000L, 1368307071000L, 1368311118000L,
            1368310664000L, 1368310943000L, 1368310735000L, 1370549961000L
        };
        
        loadScript("src/test/json/test2.js");
        loadScript("src/test/javascript/cluster4.js");
         
        NativeArray clusters = (NativeArray)exec("timeline.clusterGenerator.getClusters(0.0005, 1)");
        
        for (int i=0; i<clusters.getLength(); ++i) {
            /*
            NativeArray items = (NativeArray)((NativeObject)clusters.get(i, null)).get("items", null);
            for (int j=0; j<items.getLength(); ++j) {
                NativeObject item = (NativeObject)items.get(j, null);
                System.out.println(i + ": " + item.get("id", null));
            }
            */
            checkDate(TEST_CLUSTER_STARTS[i], (NativeObject)clusters.get(i, null));
        }
    }

    // -------------------------------------------------------------------------

    private void checkCluster(long[] refs, NativeObject cluster) throws Throwable {
        NativeArray items = (NativeArray)cluster.get("items", null);
        then(items.getLength()).isEqualTo(refs.length);

        System.out.println(String.format("Checking %d items", refs.length));

        int i = 0;
        for (long ref: refs) {
            System.out.println("Checking " + ref);
            checkDate(ref, (NativeObject)items.get(i++, null));
        }
    }

    private void checkDate(long ref, NativeObject item) throws Throwable {
        Date start = (Date)cx.jsToJava(item.get("start", null), Date.class);
        then(start.getTime()).isEqualTo(ref);
    }
    
    
}