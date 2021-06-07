package com.rtm516.stackparser;

import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class StackTestOutput {
    private String exception;
    private String description;
    private int lines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof StackException)) return false;

        StackException stackException = (StackException) o;

        return stackException.getException().equals(exception) && stackException.getDescription().equals(description) && stackException.getLines().size() == lines;
    }
}
