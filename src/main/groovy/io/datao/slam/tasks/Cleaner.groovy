package io.datao.slam.tasks

import io.datao.slam.models.SlamStructure

/**
 * Created by robertarnold on 9/18/16.
 */
class Cleaner {

    static void cleanOutputDirectory(SlamStructure slamStructure) {
        purgeDirectory(new File(slamStructure.getOutputSource()))
        purgeDirectory(new File(slamStructure.getTempSource()))
    }

    static void purgeDirectory(File dir) {
        for (File fileListing: dir.listFiles()) {
            if (fileListing.isDirectory()) purgeDirectory(fileListing);
            fileListing.delete();
        }
    }
}
