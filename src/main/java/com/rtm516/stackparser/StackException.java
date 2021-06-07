package com.rtm516.stackparser;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
public class StackException {
    public static Pattern EXCEPTION_PATTERN = Pattern.compile("([\\w\\d\\.]+(exception|throwable))(\\:\\s*(.*))?$", Pattern.CASE_INSENSITIVE);

    private String exception;
    private String description;
    private List<StackLine> lines;

    public StackException(Matcher matcher) {
        this.exception = matcher.group(1);
        this.description = matcher.group(4) != null ? matcher.group(4) : "";

        this.lines = new ArrayList<>();
    }
}
