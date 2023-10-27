package unicash.commons.util;

import static unicash.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import unicash.logic.parser.Prefix;

public class CommandUsage {
    private final String commandWord;
    private final String description;
    private final String argument;
    private final List<Parameter> parameters;
    private final String example;

    private CommandUsage(
            String commandWord,
            String description,
            String argument,
            List<Parameter> parameters,
            String example
    ) {
        requireAllNonNull(commandWord, description, parameters, example);
        this.commandWord = commandWord;
        this.description = description;
        this.argument = argument;
        this.parameters = parameters;
        this.example = example;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append(commandWord).append(": ").append(description).append("\n\n");

        if (argument != null) {
            stringBuilder.append("Argument: ").append(argument).append("\n\n");
        }

        if (parameters.size() > 0) {
            // Order parameters in order of
            // 1. Mandatory
            // 2. Optional
            // 3. Variadic
            Collections.sort(parameters);
            stringBuilder.append("Parameters: ");
            var parameterString = parameters
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" "));
            stringBuilder.append(parameterString).append("\n\n");
        }
        stringBuilder.append(example).append("\n\n");

        return stringBuilder.toString();
    }

    public static class Builder {
        private String commandWord = null;
        private String description = null;
        private String argument = null;
        private final List<Parameter> parameters = new ArrayList<>();
        private String example = null;

        public Builder setCommandWord(String commandWord) {
            this.commandWord = commandWord;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setArgument(String argument) {
            this.argument = argument;
            return this;
        }

        public Builder addPlainParameter(Prefix prefix, String name) {
            return addParameter(prefix, name, false, false);
        }

        public Builder addParameter(
                Prefix prefix,
                String name,
                boolean isOptional,
                boolean isVariadic
        ) {
            this.parameters.add(
                    new Parameter(
                            prefix.getPrefix(),
                            name,
                            isOptional,
                            isVariadic
                    )
            );
            return this;
        }

        public Builder setExample(String example) {
            this.example = example;
            return this;
        }

        public CommandUsage build() {
            requireAllNonNull(commandWord, description, example);

            return new CommandUsage(commandWord, description, argument, parameters, example);
        }
    }

    public static class Parameter implements Comparable<Parameter> {
        private final String prefix;
        private final String name;
        private final boolean isOptional;
        private final boolean isVariadic;

        public Parameter(
                String prefix,
                String name,
                boolean isOptional,
                boolean isVariadic
        ) {
            this.prefix = prefix;
            this.name = name;
            this.isOptional = isOptional;
            this.isVariadic = isVariadic;
        }

        @Override
        public int compareTo(Parameter other) {
            if (isVariadic != other.isVariadic) {
                // All variadic parameters go to the end
                return isVariadic ? 1 : -1;
            }

            if (isOptional != other.isOptional) {
                // Prioritise mandatory parameters first
                return isOptional ? 1 : -1;
            }

            // If all the same, sort by prefix lexicographical order
            return prefix.compareTo(other.prefix);
        }

        @Override
        public String toString() {
            var stringBuilder = new StringBuilder();
            if (isOptional) {
                stringBuilder.append("[");
            }
            stringBuilder.append(prefix).append(name);
            if (isOptional) {
                stringBuilder.append("]");
            }
            if (isVariadic) {
                stringBuilder.append("...");
            }
            return stringBuilder.toString();
        }
    }
}
