package com.wpmtec.buildersBoard.rest.controller;

public interface RestControllerInterface<T> {
    boolean validateInput(T inputData);
}
