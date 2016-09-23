package io.datao.slam.builders

import io.datao.slam.models.SlamStep
import io.datao.slam.models.SlamStructure
import io.datao.slam.models.SlamTask

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
            /*
                Default objects tested first
                then the language,
                then variable strings
             */
            slamStructure.setDataSource(configObject.get("data", "data").toString())
            slamStructure.setOutputSource(configObject.get("out", "out").toString())
            slamStructure.setScriptSource(configObject.get("scripts", "scripts").toString())
            slamStructure.setTempSource(configObject.get("temp", "temp").toString())

            if (configObject.languages instanceof List) {
                slamStructure.setLanguages(configObject.get("languages", ["r", "python"]) as List<String>)
            } else {
                slamStructure.setLanguages(["r", "python"])
            }

            slamStructure.setVariableString(configObject.get("variableString", "").toString())

            /*
                More difficult - are the tasks themselves as well as steps
             */
            def List<SlamTask> slamTaskList = new ArrayList<SlamTask>()

            if (configObject.tasks instanceof  List) {
                ((List)configObject.tasks).forEach({
                    taskItem ->
                        if (taskItem instanceof Map) {
                            def SlamTask slamTask = new SlamTask()
                            slamTask.setArgString(taskItem.get("argString", "").toString())
                            slamTask.setId(taskItem.get("id", "").toString())
                            slamTask.setLanguage(taskItem.get("language", "").toString())
                            slamTask.setScriptName(taskItem.get("scriptName", "").toString())

                            slamTaskList.add(slamTask)
                        }
                })
            }
            slamStructure.setTasks(slamTaskList)

            def List<SlamStep> slamStepList = new ArrayList<SlamStep>()

            if (configObject.steps instanceof  List) {
                ((List)configObject.steps).forEach({
                    stepItem ->
                        if (stepItem instanceof Map) {
                            def SlamStep slamStep = new SlamStep()
                            slamStep.setSequence(stepItem.get("sequence").toString().toInteger())
                            slamStep.setTaskId(stepItem.get("taskId", "").toString())

                            slamStepList.add(slamStep)
                        }
                })
            }
            slamStructure.setSteps(slamStepList)


        } else {
            slamStructure = buildEnvModel()
        }

        return slamStructure
    }
}
