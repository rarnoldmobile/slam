package io.datao.slam

import groovy.util.CliBuilder
import io.datao.slam.builders.SlamStructureBuilder
import io.datao.slam.models.SlamStructure
import io.datao.slam.tasks.Cleaner
import io.datao.slam.tasks.Initializer

import groovy.json.JsonSlurper
import io.datao.slam.tasks.Validator
import org.apache.commons.cli.Option
/**
 * Created by robertarnold on 9/17/16.
 */


class App {

    private cli

    static void main(String[] args) {

        def cli =  setupEnvironment()
        def configText = findInit()
        def SlamStructure slamStructure = new SlamStructure()
        slamStructure = SlamStructureBuilder.buildEnvModel()

        if (!configText.isEmpty() && configText != "") {
            System.out.println("Custom build guidelines detected")
            def jsonSlurper = new JsonSlurper()
            def configObject = jsonSlurper.parseText(configText)
            slamStructure = SlamStructureBuilder.buildEnvModel(configObject)
        }

        OptionAccessor options = cli.parse(args)
        runTasks(slamStructure, options)


    }

    static setupEnvironment()
    {
        def cli = new CliBuilder(
                    usage: 'slam [tasks]',
                    header: 'Options:')

        cli.with {
            i longOpt: 'init', 'Initialize a new project directory', args: 0
            c longOpt: 'clean', 'Clean the project directory', args: 0
            v longOpt: 'validate', 'Validate the config file', args: 0
        }

        return cli
    }

    static runTasks(SlamStructure slamStructure, OptionAccessor oa) {

        if (!oa)
            return

        if (oa.i) {
            System.out.println("Init environment")
            Initializer.runInitializer(slamStructure)
        }

        if (oa.c) {
            System.out.println("Running Task: Environment Clean")
            Cleaner.cleanOutputDirectory(slamStructure)
        }

        if (oa.v) {
            System.out.println("Validating Configuration File")
            Validator.performValidation(slamStructure)
        }

    }


    static String findInit() {
        String fileContents = ""

        try {
            fileContents = new File('config.slam').text
        }

        catch (e) {
            if (e instanceof java.io.FileNotFoundException) {
                System.out.println("config.slam not found - using default template")
                fileContents = ""
            } else {
                System.out.println(e.getClass())
                System.out.println(e.getMessage())
            }
        }

        return fileContents

    }


}
