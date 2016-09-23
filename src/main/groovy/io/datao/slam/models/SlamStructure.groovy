package io.datao.slam.models

/**
 * Created by robertarnold on 9/18/16.
 */
class SlamStructure {

    def String scriptSource
    def String dataSource
    def String tempSource
    def String outputSource
    def List<String> languages

    def List<SlamStep> steps
    def List<SlamTask> tasks
    def String variableString

}
