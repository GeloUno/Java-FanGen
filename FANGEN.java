import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Java 11

class Fan {
    private String fan = "";

    public String getFan() {
        return fan;
    }


    protected int heightFan;
    protected char[][] fanChar = null;

    public int getHeightFan() {
        return heightFan;
    }

    public void setHeightFan(int heightFan) {
        this.heightFan = Math.abs(heightFan);
        this.fanChar = new char[this.heightFan * 2][this.heightFan * 2]; // TODO: maybe it have to be one other function
    }


    protected void PrintFan() {
        System.out.printf("\n");
        for (int axisY = 0; axisY < heightFan * 2; axisY++) {
            for (int axisX = 0; axisX < heightFan * 2; axisX++) {
                fan = fan + fanChar[axisX][axisY];
            }
            fan = fan + "\n";
        }
    }
}

abstract class QuarterFanBuild extends Fan {

    abstract boolean Build(int axisX, int axisY);


    public void Build() {
        for (int axisX = 0; axisX < getHeightFan(); axisX++) {
            for (int axisY = 0; axisY < getHeightFan(); axisY++) {
                if (Build(axisX, axisY)) {
                    this.fanChar[axisX][axisY] = '*';
                } else {
                    this.fanChar[axisX][axisY] = '.';
                }
            }
        }
    }
}
class LeftQuarterFanBuild extends QuarterFanBuild {


    @Override
    public boolean Build(int axisX, int axisY) {
        if (axisX <= axisY) {
           return true;
        }
        return  false;
    }
}

class RightQuarterFanBuild extends QuarterFanBuild {

    @Override
    public boolean Build(int axisX, int axisY) {
        if (axisX >= axisY) {
           return true;
        }
        return false;
    }
}

abstract class RotateBuildFan extends Fan {

    abstract void Rotate(Fan a);
}

class QuarterRotateBuildFan extends RotateBuildFan {

    void Rotate(Fan a) {
        int axisXhelper = 0;
        for (int axisX = a.heightFan - 1; axisX >= 0; axisX--) {
            for (int axisY = 0; axisY < a.heightFan; axisY++) {
                a.fanChar[axisXhelper][axisY + a.heightFan] = a.fanChar[axisX][axisY];
            }
            axisXhelper++;
        }
    }
}

class HalfRotateBuilderFan extends RotateBuildFan {

    void Rotate(Fan a) {
        int axiosXhelper = a.heightFan;
        int axisYhelper;
        for (int axisX = a.heightFan - 1; 0 <= axisX; axisX--) {
            axisYhelper = 0;
            for (int axisY = a.heightFan * 2 - 1; 0 <= axisY; axisY--) {
                a.fanChar[axiosXhelper][axisYhelper] = a.fanChar[axisX][axisY];
                axisYhelper++;
            }
            axiosXhelper++;
        }
    }
}


public class FANGEN {
    public static void main(String[] args) {

        List<Integer> fans = new ArrayList<>();
        String s1;
        int heighFan = 0;

        QuarterFanBuild fanObjcect = null;

        do {
            Scanner s = new Scanner(System.in);
            try {
                s1 = (s.nextLine());
                heighFan = Integer.parseInt(s1);
                if (Math.abs(heighFan) <= 200) {
                    fans.add(heighFan);
                }
            } catch (Exception ex) {
                heighFan = 1;// repeat do while when user insert other that int
            }
        } while (heighFan != 0);


        for (int f : fans) {
            heighFan = f;

            if (heighFan == 0) {
                System.exit(0);
            } else if (heighFan > 0) {
                fanObjcect = new LeftQuarterFanBuild();
            } else if (heighFan < 0) {
                fanObjcect = new RightQuarterFanBuild();
            }
            fanObjcect.setHeightFan(heighFan);


            fanObjcect.Build();

            RotateBuildFan rotateBuilderFan = new QuarterRotateBuildFan();
            rotateBuilderFan.Rotate(fanObjcect);
            rotateBuilderFan = new HalfRotateBuilderFan();
            rotateBuilderFan.Rotate(fanObjcect);

            fanObjcect.PrintFan();
            System.out.printf(fanObjcect.getFan());

        }
        System.exit(0);
    }
}
