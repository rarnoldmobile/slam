package io.datao.slam.tasks

import io.datao.slam.models.SlamStructure

/**
 * Created by robertarnold on 9/18/16.
 */
class Initializer {

    public static void runInitializer(SlamStructure slamStructure) {
        /*
            For each of the various sections, create our file system layout
         */

        //Create data directory
        new File(slamStructure.getDataSource()).mkdir()
        new File(slamStructure.getOutputSource()).mkdir()
        new File(slamStructure.getScriptSource()).mkdir()
        new File(slamStructure.getTempSource()).mkdir()

        /* Create all of our languages */
        for (String language : slamStructure.getLanguages()) {
            new File(slamStructure.getScriptSource(), language).mkdir()
        }

    }
}
