package io.datao.slam.models

/**
 * Created by robertarnold on 9/22/16.
 */
public enum ValidatorResponseStatus {

    SUCCESS('success', 'config.slam is completely valid'),
    FAIL('fail', 'config.slam is invalid'),
    WARNING('warning', 'config.slam may be missing some components')

    final String id;
    final String desc;
    static final Map map

    static {
        map = [:] as TreeMap
        values().each{ ResponseStatus ->
            map.put(ResponseStatus.id, ResponseStatus)
        }

    }

    private ValidatorResponseStatus(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    static getValidatorResponseStatus( id ) {
        map[id]
    }
}

class ValidatorResponse {

    def ValidatorResponseStatus validatorResponseStatus
    def List<String> messages

}
