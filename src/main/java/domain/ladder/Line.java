package domain.ladder;

import domain.player.Player;
import java.util.ArrayList;
import java.util.List;

public class Line {

    private final List<LinePoint> points;

    public Line(List<LinePoint> points) {
        validate(points);
        this.points = new ArrayList<>(points);
    }

    private void validate(List<LinePoint> points) {
        if (hasDuplicatePosition(points)) {
            throw new IllegalArgumentException("중복된 위치가 존재합니다.");
        }
    }

    private boolean hasDuplicatePosition(List<LinePoint> points) {
            return points.size() != points.stream()
                    .map(LinePoint::getPosition)
                    .distinct()
                    .count();
        }

    public void move(Player player) {
        LinePoint findPoint = points.stream()
                .filter(point -> player.isSamePosition(point.getPosition()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Line이 잘못된 값으로 생성되었습니다."));

        player.move(findPoint.getDirectionValue());
    }

    public List<LinePoint> getPoints() {
        return points;
    }
}
