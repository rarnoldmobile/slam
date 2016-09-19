package io.datao.slam.builders

import io.datao.slam.models.SlamStructure

/**
 * Created by robertarnold on 9/18/16.
 */
class SlamStructureBuilder {

    public static SlamStructure buildEnvModel() {

        SlamStructure slamStructure = new SlamStructure()
        slamStructure.setDataSource("data")
        slamStructure.setOutputSource("out")
        slamStructure.setScriptSource("scripts")
        slamStructure.setTempSource("temp")
        slamStructure.setLanguages(["r", "python"])

        return slamStructure
    }

    public static SlamStructure buildEnvModel(Object configObject) {
        System.out.println(configObject.toString())
        SlamStructure slamStructure = new SlamStructure()

        if (configObject instanceof Map) {

            slamStructure.setDataSource(configObject.get("data", "data").toString())
            slamStructure.setOutputSource(configObject.get("out", "out").toString())
            slamStructure.setScriptSource(configObject.get("scripts", "scripts").toString())
            slamStructure.setTempSource(configObject.get("temp", "temp").toString())

            if (configObject.languages instanceof List) {
                slamStructure.setLanguages(configObject.get("languages", ["r", "python"]) as List<String>)
            } else {
                slamStructure.setLanguages(["r", "python"])
            }
        } else {
            slamStructure = buildEnvModel()
        }

        return slamStructure
    }
}
