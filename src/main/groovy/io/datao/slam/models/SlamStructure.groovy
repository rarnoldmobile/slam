package io.datao.slam.models

/**
 * Created by robertarnold on 9/18/16.
 */
class SlamStructure {

    private String scriptSource
    private String dataSource
    private String tempSource
    private String outputSource
    private List<String> languages

    String getScriptSource() {
        return scriptSource
    }

    void setScriptSource(String scriptSource) {
        this.scriptSource = scriptSource
    }

    String getDataSource() {
        return dataSource
    }

    void setDataSource(String dataSource) {
        this.dataSource = dataSource
    }

    String getTempSource() {
        return tempSource
    }

    void setTempSource(String tempSource) {
        this.tempSource = tempSource
    }

    String getOutputSource() {
        return outputSource
    }

    void setOutputSource(String outputSource) {
        this.outputSource = outputSource
    }

    List<String> getLanguages() {
        return languages
    }

    void setLanguages(List<String> languages) {
        this.languages = languages
    }




}
