package model;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static  application.Application.camp;

public class Perimeter {
    private List<String> perimeterLimits;
    private List<String> perimeter;

    public Perimeter(String cell) {
        perimeterLimits = Arrays.asList(cell.split("_"));
        this.perimeter = (Arrays.asList(adjacentCell(-1,-1), adjacentCell(-1,0), adjacentCell(-1,1), adjacentCell(0,1), adjacentCell(0,-1), adjacentCell(1,-1), adjacentCell(1,0), adjacentCell(1,1))).stream().filter(c -> exist(c)).collect(Collectors.toList());

    }

    public List<String> get() {
        return  perimeter;
    }

    private boolean exist(String cell) {
        return camp().containsKey(cell);
    }

    private String adjacentCell(Integer deviationI, Integer deviationJ){
        return (perimeterLimitComponentI()+deviationI)+"_"+(perimeterLimitComponentJ()+deviationJ);
    }

    private Integer perimeterLimitComponentI(){
        return Integer.parseInt(perimeterLimits.get(0));
    }

    private Integer perimeterLimitComponentJ(){
        return Integer.parseInt(perimeterLimits.get(1));
    }
}
