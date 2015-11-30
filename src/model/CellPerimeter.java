package model;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static  application.App.camp;

public class CellPerimeter implements Perimeter {
    private List<String> perimeterLimits;
    @Override
    public List<String> get(String cell) {
        perimeterLimits = Arrays.asList(cell.split("_"));
        return  (Arrays.asList(adjacentCell(-1,-1), adjacentCell(-1,0), adjacentCell(-1,1), adjacentCell(0,1), adjacentCell(0,-1), adjacentCell(1,-1), adjacentCell(1,0), adjacentCell(1,1))).stream().filter(c -> exist(c)).collect(Collectors.toList());
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
