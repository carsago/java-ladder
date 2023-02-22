package view;

import static java.util.stream.Collectors.toList;

import domain.LadderResultRequest;
import domain.ladder.LadderHeight;
import domain.ladder.LadderResult;
import domain.player.Name;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String VALUE_DELIMITER = ",";
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<Name> readNames() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");

        String rawNames = scanner.nextLine();
        return Arrays.stream(rawNames.split(VALUE_DELIMITER))
                .map(String::trim)
                .map(Name::new)
                .collect(toList());
    }

    public LadderHeight readLadderHeight() {
        System.out.println("최대 사다리 높이는 몇 개인가요?");

        int height = parseInt(scanner.nextLine());
        return new LadderHeight(height);
    }

    public List<LadderResult> readLadderResults() {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");

        String ladderResults = scanner.nextLine();
        return Arrays.stream(ladderResults.split(VALUE_DELIMITER))
                .map(String::trim)
                .map(LadderResult::new)
                .collect(toList());
    }

    public LadderResultRequest readSpecificResult() {
        System.out.println("결과를 보고 싶은 사람은?");
        return new LadderResultRequest(scanner.nextLine());
    }

    private static int parseInt(String rawLadderHeight) {
        try {
            return Integer.parseInt(rawLadderHeight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("사다리의 높이는 숫자여야합니다. " +
                    "입력값: %s", rawLadderHeight));
        }
    }
}
