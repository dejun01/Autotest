package com.opencommerce.odoo.constant;


/**
 * Refer to this document to know exactly relation command <li>https://www.odoo.com/documentation/11.0/reference/orm.html#odoo.models.Model.create</li>
 */
public enum RelationCommand {
    NEW_RECORD(0),
    UPDATE_RECORD(1),
    REMOVE_RECORD(2),
    REMOVE_NOT_DELETE_RECORD(3),
    ADD_EXISTING_RECORD(4),
    ;
    private final int commandCode;

    RelationCommand(int commandCode) {
        this.commandCode = commandCode;
    }

    public int getCode() {
        return this.commandCode;
    }

}
