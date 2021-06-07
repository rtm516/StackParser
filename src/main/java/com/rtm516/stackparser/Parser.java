package com.rtm516.stackparser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Parser {

    public static List<StackException> parse(String log) {
        String[] lines = log.split("\n");

        List<StackException> exceptions = new ArrayList<>();

        StackException parsedException = null;

        for (String line : lines) {
            line = line.trim();
            Matcher exceptionMatcher = StackException.EXCEPTION_PATTERN.matcher(line);

            if (!exceptionMatcher.find() && parsedException == null) {
                continue;
            }

            if (parsedException == null) {
                parsedException = new StackException(exceptionMatcher);
                continue;
            }

            Matcher lineMatcher = StackLine.LINE_PATTERN.matcher(line);
            if (!lineMatcher.find()) {
                exceptions.add(parsedException);
                parsedException = null;
                continue;
            }

            parsedException.getLines().add(new StackLine(lineMatcher));
        }

        if (parsedException != null) {
            exceptions.add(parsedException);
        }

        return exceptions;
    }
}
