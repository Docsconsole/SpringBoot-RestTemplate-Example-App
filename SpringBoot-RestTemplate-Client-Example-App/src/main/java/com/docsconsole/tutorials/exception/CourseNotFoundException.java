package com.docsconsole.tutorials.exception;


public class CourseNotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public CourseNotFoundException(int id) {
        super("Course does not existed with id: " + id);
    }
}
