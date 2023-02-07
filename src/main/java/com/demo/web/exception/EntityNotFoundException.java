package com.demo.web.exception;




import com.demo.web.exception.handle.ServiceError;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityNotFoundException extends RuntimeException{

    private String entityName;
    private String entityId;

    public EntityNotFoundException() {
        super(ServiceError.ENTITY_NOT_FOUND.getMessageKey(), null);
    }

    public EntityNotFoundException(String entityName, String entityId) {
        super(ServiceError.ENTITY_NOT_FOUND.getMessageKey(), null);
        this.entityId = entityId;
        this.entityName = entityName;
    }

}
