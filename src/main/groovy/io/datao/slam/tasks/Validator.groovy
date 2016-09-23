package io.datao.slam.tasks

import io.datao.slam.models.SlamStructure
import io.datao.slam.models.ValidatorResponse
import io.datao.slam.models.ValidatorResponseStatus

/**
 * Created by robertarnold on 9/22/16.
 */
class Validator {

    public static ValidatorResponse validateConfig(SlamStructure slamStructure) {

        def ValidatorResponse validatorResponse = new ValidatorResponse()
        validatorResponse.messages = new ArrayList<String>()

        def int failCount = 0
        def int warningCount = 0

        /*
            Test if tasks and steps are defined
         */
        if (slamStructure.getTasks().size() == 0) {
            warningCount = warningCount + 1
            validatorResponse.messages.add("No Tasks specified - run option not available")
        }

        if (slamStructure.getSteps().size() == 0) {
            warningCount = warningCount + 1
            validatorResponse.messages.add("No Steps specified - run option not available")
        }


        if (failCount > 0) {
            validatorResponse.setValidatorResponseStatus(ValidatorResponseStatus.FAIL)
        } else if (warningCount > 0) {
            validatorResponse.setValidatorResponseStatus(ValidatorResponseStatus.WARNING)
        } else {
            validatorResponse.setValidatorResponseStatus(ValidatorResponseStatus.SUCCESS)
        }

        validatorResponse.messages.add("Validation Finished")

        return validatorResponse

    }

    public static void performValidation(SlamStructure slamStructure) {
        ValidatorResponse validatorResponse = validateConfig(slamStructure)

        if (validatorResponse.validatorResponseStatus == ValidatorResponseStatus.FAIL) {
            System.out.println("Validation Failed")
        } else if (validatorResponse.validatorResponseStatus == ValidatorResponseStatus.WARNING) {
            System.out.println("Validation Warning")
        } else {
            System.out.println("Validation Success")
        }

        validatorResponse.messages.forEach({
            message ->
                System.out.println(message)
        })
    }
}
