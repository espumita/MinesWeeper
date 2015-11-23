package model;

import java.util.List;

public interface Perimeter {
    List<String> get(String cell);

    int perimeterEnd(int position);

    int perimeterStart(int position);

    void markCell(String cell);
}
