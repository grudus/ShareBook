package com.pwr.sharebook.common.exceptions

class CannotFindIdException(message: String) : RuntimeException(message) {
    constructor() : this("Could not find required id")
}
