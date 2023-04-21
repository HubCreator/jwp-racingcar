package racingcar.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Names {

    private final List<Name> names;

    public Names(final String names) {
        final List<String> nameList = splitByDelimiter(names);
        validateDuplicatedCarNames(nameList);
        this.names = nameList.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private  List<String> splitByDelimiter(final String names) {
        final String[] split = names.split(",");
        return Arrays.stream(split)
                .map(String::strip)
                .collect(Collectors.toList());
    }

    private void validateDuplicatedCarNames(final List<String> input) {
        if (input.size() != input.stream().distinct().count()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    public List<Name> getNames() {
        return new ArrayList<>(names);
    }
}
